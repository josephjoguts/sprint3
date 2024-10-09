package ru.practicum.temperaturemanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.temperaturemanager.controller.request.ConfigurationRequest;
import ru.practicum.temperaturemanager.controller.request.DeviceOldRequest;
import ru.practicum.temperaturemanager.controller.request.DeviceRequest;
import ru.practicum.temperaturemanager.controller.request.RoomRequest;
import ru.practicum.temperaturemanager.controller.response.ConfigurationResponse;
import ru.practicum.temperaturemanager.controller.response.DeviceResponse;
import ru.practicum.temperaturemanager.service.UserConfigService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserConfigController {
    private final UserConfigService userConfigService;

    @PostMapping("configuration")
    public ResponseEntity<Void> createConfiguration(@RequestBody ConfigurationRequest request) {
        userConfigService.createConfiguration(request);
        return ResponseEntity.ok(null);
    }

    @PutMapping("configuration")
    public ResponseEntity<Void> updateConfiguration(@RequestBody ConfigurationRequest request) {
        userConfigService.updateConfig(request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("configuration/{id}")
    public ResponseEntity<Void> updateConfiguration(@PathVariable("id") Long id) {
        userConfigService.deleteConfig(id);
        return ResponseEntity.ok(null);
    }

    @PostMapping("configuration/send/{id}")
    public ResponseEntity<Void> sendConfiguration(@PathVariable("id") Long id) {
        userConfigService.sendConfig(id);
        return ResponseEntity.ok(null);
    }


    @GetMapping("configuration")
    public ResponseEntity<List<String>> getConfigurations() {
        return ResponseEntity.ok(userConfigService.getConfigurations());
    }

    @GetMapping("configuration/{configName}")
    public ResponseEntity<ConfigurationResponse> getConfigurations(@PathVariable("configName") String configName) {
        return ResponseEntity.ok(userConfigService.getConfiguration(configName));
    }

    @PostMapping("device")
    public ResponseEntity<Void> createDevice(@RequestBody DeviceRequest request) {
        userConfigService.addDevice(request);
        return ResponseEntity.ok(null);
    }

    @GetMapping("device")
    public ResponseEntity<List<DeviceResponse>> getDevices() {
        return ResponseEntity.ok(userConfigService.getDevices());
    }

    @DeleteMapping("device/{id}")
    public ResponseEntity<List<DeviceResponse>> deleteDevice(@PathVariable("id") Long id) {
        userConfigService.deleteDevice(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("room")
    public ResponseEntity<Void> updateRoom(@RequestBody RoomRequest roomRequest) {
        userConfigService.enableRoom(roomRequest);
        return ResponseEntity.ok(null);
    }

    @PutMapping("devices/{deviceId}/status")
    @Deprecated
    public ResponseEntity<Void> updateDevice(@PathVariable("deviceId") Long deviceId,@RequestBody DeviceOldRequest request) {
        userConfigService.enableRoom(deviceId, request);
        return ResponseEntity.ok(null);
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from temperature manager");
    }
}
