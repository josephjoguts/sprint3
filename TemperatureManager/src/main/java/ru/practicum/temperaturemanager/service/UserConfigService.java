package ru.practicum.temperaturemanager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.practicum.temperaturemanager.controller.request.ConfigurationRequest;
import ru.practicum.temperaturemanager.controller.request.DeviceOldRequest;
import ru.practicum.temperaturemanager.controller.request.DeviceRequest;
import ru.practicum.temperaturemanager.controller.request.RoomRequest;
import ru.practicum.temperaturemanager.controller.response.ConfigurationResponse;
import ru.practicum.temperaturemanager.controller.response.DeviceResponse;
import ru.practicum.temperaturemanager.controller.response.RoomResponse;
import ru.practicum.temperaturemanager.repository.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserConfigService {
    private final DeviceRepository deviceRepository;
    private final ConfigRepository configRepository;
    private final RoomRepository roomRepository;
    private final RestTemplate restTemplate;
    @Value("${temperature.monitor.url}")
    private String temperatureMonitorUrl;

    @Transactional
    public void createConfiguration(ConfigurationRequest request) {
        configRepository.save(convertToConfigEntity(request));
    }

    @Transactional
    public void updateConfig(ConfigurationRequest request) {
        ConfigEntity configInDatabase = configRepository.findById(request.getConfigId()).orElseThrow(() -> new IllegalArgumentException("No config"));
        ConfigEntity configFromRequest = convertToConfigEntity(request);
        configInDatabase.setConfigName(configFromRequest.getConfigName());
        configInDatabase.setTargetTemperature(configFromRequest.getTargetTemperature());
        configInDatabase.setRooms(configFromRequest.getRooms());
        configRepository.save(configInDatabase);
    }

    @Transactional
    public List<String> getConfigurations() {
        List<ConfigEntity> configs = configRepository.findAll();
        return configs.stream().map(ConfigEntity::getConfigName).toList();
    }

    @Transactional
    public ConfigurationResponse getConfiguration(String configName) {
        ConfigEntity configEntity = configRepository.findByConfigName(configName).orElse(new ConfigEntity());
        ConfigurationResponse configurationResponse = new ConfigurationResponse().setConfigName(configEntity.getConfigName()).setTargetTemperature(configEntity.getTargetTemperature());
        List<RoomResponse> rooms = configEntity.getRooms().stream().map(
                x -> {
                    DeviceEntity device = x.getDevice();
                    RoomResponse roomResponse = new RoomResponse();
                    roomResponse.setRoomName(x.getRoomName());
                    roomResponse.setId(x.getId());
                    roomResponse.setIsDeviceEnabled(x.getIsDeviceEnabled());
                    roomResponse.setDevice(new DeviceResponse()
                                    .setId(device.getId())
                            .setName(device.getName())
                            .setSerial(device.getSerial())
                            .setDescription(device.getDescription()));
                    return roomResponse;
                }
        ).toList();
        configurationResponse.setId(configEntity.getId());
        return configurationResponse.setRooms(rooms);
    }

    @Transactional
    public void deleteConfig(Long id) {
        configRepository.deleteById(id);
    }

    @Transactional
    public void addDevice(DeviceRequest request) {
        DeviceEntity device = new DeviceEntity()
                .setName(request.getName())
                .setDescription(request.getDescription())
                .setSerial(request.getSerial());
        deviceRepository.save(device);
    }

    @Transactional
    public List<DeviceResponse> getDevices() {
        List<DeviceEntity> devices = deviceRepository.findAll();
        return devices.stream().map(x-> new DeviceResponse()
                .setId(x.getId())
                .setDescription(x.getDescription())
                .setName(x.getName())
                .setSerial(x.getSerial())).toList();
    }

    @Transactional
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    @Transactional
    public void enableRoom(RoomRequest request) {
        RoomEntity room = roomRepository.findById(request.getRoomId()).orElseThrow(() -> new IllegalArgumentException("Room not found"));
        room.setIsDeviceEnabled(request.getIsDeviceEnabled());
        roomRepository.save(room);
        updateRemoteConfig(configRepository.findById(room.getConfig().getId()).orElseThrow(() -> new IllegalArgumentException("This is bad exception")));

    }

    @Transactional
    public void enableRoom(Long deviceId, DeviceOldRequest request) {
        DeviceEntity deviceEntity = deviceRepository.findById(deviceId).orElseThrow(() -> new IllegalArgumentException("No device"));
        RoomEntity room = deviceEntity.getRoom();
        if (room == null) {
            throw new IllegalArgumentException("No room");
        }
        room.setIsDeviceEnabled(request.getStatus().equals("on"));
        roomRepository.save(room);
        updateRemoteConfig(configRepository.findById(room.getConfig().getId()).orElseThrow(() -> new IllegalArgumentException("This is bad exception")));
    }

    @Transactional
    public void sendConfig(Long id) {
        ConfigEntity config = configRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Config not found"));
        updateRemoteConfig(config);
    }

    public void updateRemoteConfig(ConfigEntity entity) {
        RemoteConfigRequest request = new RemoteConfigRequest();
        request.setConfigName(entity.getConfigName());
        request.setTargetTemperature(entity.getTargetTemperature());
        List<RemoteConfigRequest.Room> rooms = entity.getRooms().stream().map(x -> {
            RemoteConfigRequest.Room room = new RemoteConfigRequest.Room();
            room.setRoomName(x.getRoomName());
            room.setId(x.getId());
            room.setIsDeviceEnabled(x.getIsDeviceEnabled());
            room.setDevice(new RemoteConfigRequest.Device().setDeviceId(x.getDevice().getId()).setName(x.getDevice().getName()).setSerial(x.getDevice().getSerial()));
            return room;
        }).toList();

        request.setRooms(rooms);

        CompletableFuture.runAsync(() -> {
            try {
                String result = restTemplate.postForObject(temperatureMonitorUrl + "/config", request, String.class);
                log.debug("Successfully send to tempMonitor result" + result);
            } catch (Exception e) {
                log.debug("Failed to update remote config" + e);
                throw new RuntimeException("Failed to update remote config", e);
            }
        });
    }
    private ConfigEntity convertToConfigEntity(ConfigurationRequest request) {
        ConfigEntity config = new ConfigEntity();
        config.setConfigName(request.getConfigName());
        config.setTargetTemperature(request.getTargetTemperature());
        config.setRooms(convertToRoomEntity(request.getRooms(), config));
        return config;
    }

    private List<RoomEntity> convertToRoomEntity(List<RoomRequest> rooms, ConfigEntity config) {
        return rooms.stream().map(
                x ->  {
                    DeviceEntity device = deviceRepository.findById(x.getDeviceId()).orElseThrow(() -> new RuntimeException("No device found"));
                    return new RoomEntity().setConfig(config).setRoomName(x.getName()).setIsDeviceEnabled(x.getIsDeviceEnabled()).setDevice(device);
                }).toList();
    }


}
