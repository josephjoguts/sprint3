package ru.practicum.temperaturemonitor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.practicum.temperaturemonitor.service.dto.RoomHardware;
import ru.practicum.temperaturemonitor.service.dto.TelemetryTickDto;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class HardwareServiceMock {
    private final SystemMonitorService systemMonitorService;
    private final ConcurrentHashMap<Long, RoomHardware> hardwares = new ConcurrentHashMap<>();

    @Scheduled(fixedRate = 10000)
    public void tickHardware() {
        hardwares.values().forEach(x -> {
            if (x.getIsDeviceEnabled()) {
                float currentTemperature = x.getCurrentTemperature();
                float targetTemperature = x.getTargetTemperature();
                currentTemperature = adjustTemperature(currentTemperature, targetTemperature);
                x.setCurrentTemperature(currentTemperature);
            }

            else {
                int i = new Random().nextInt(1, 10);
                boolean b = new Random().nextBoolean();
                float currentTemperature = x.getCurrentTemperature();
                float targetTemperature = x.getTargetTemperature() + ((b ? 1 : -1) * i);
                currentTemperature = adjustTemperature(currentTemperature, targetTemperature);
                x.setCurrentTemperature(currentTemperature);
            }

            TelemetryTickDto telemetryTickDto = new TelemetryTickDto()
                    .setCreatedAt(new Date())
                    .setIsDeviceEnabled(x.getIsDeviceEnabled())
                    .setCurrentTemperature(x.getCurrentTemperature())
                    .setDeviceId(x.getDeviceId())
                    .setRoomId(x.getId());

            systemMonitorService.collectTelemetryData(telemetryTickDto);
        });
    }

    public void addNewHardware(List<RoomHardware> hardwaresLst) {
        hardwaresLst.forEach(x -> {
            hardwares.put(x.getId(), x);
        });
    }

    private static float adjustTemperature(float currentTemperature, float targetTemperature) {
        float difference = targetTemperature - currentTemperature;
        float adjustment = new Random().nextFloat() * Math.signum(difference);
        if (Math.abs(difference) > 0.1) {
            currentTemperature += adjustment;
        }
        return currentTemperature;
    }
}
