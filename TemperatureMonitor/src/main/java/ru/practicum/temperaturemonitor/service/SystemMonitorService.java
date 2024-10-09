package ru.practicum.temperaturemonitor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.temperaturemonitor.controller.dto.TelemetryRequest;
import ru.practicum.temperaturemonitor.repository.TelemetryEntity;
import ru.practicum.temperaturemonitor.repository.MonitorRepository;
import ru.practicum.temperaturemonitor.service.dto.TelemetryTickDto;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class SystemMonitorService {
    private final MonitorRepository monitorRepository;

    @Transactional
    public void collectTelemetryData(TelemetryTickDto tickDto) {
        TelemetryEntity telemetryEntity = new TelemetryEntity()
                .setCreatedAt(tickDto.getCreatedAt())
                .setCurrentTemperature(tickDto.getCurrentTemperature())
                .setRoomId(tickDto.getRoomId())
                .setDeviceId(tickDto.getDeviceId())
                .setOn(tickDto.getIsDeviceEnabled());

        monitorRepository.save(telemetryEntity);
    }

    @Transactional
    public void addTelemetry(TelemetryRequest telemetryRequest) {
        TelemetryEntity telemetryEntity = new TelemetryEntity()
                .setCreatedAt(new Date())
                .setDeviceId(telemetryRequest.getDeviceId())
                .setCurrentTemperature(telemetryRequest.getCurrentTemperature())
                .setRoomId(-1L);

        monitorRepository.save(telemetryEntity);
    }
}
