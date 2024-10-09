package ru.practicum.temperaturemonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.temperaturemonitor.controller.dto.UserSettingsRequest;
import ru.practicum.temperaturemonitor.repository.MonitorRepository;
import ru.practicum.temperaturemonitor.repository.TelemetryEntity;
import ru.practicum.temperaturemonitor.service.dto.TelemetryTickDto;

import java.util.List;

@Service
public class UserSettingsService {
    @Autowired
    private MonitorRepository monitorRepository;

    public List<TelemetryTickDto> getTelemetry(UserSettingsRequest request) {
        List<TelemetryEntity> entities = monitorRepository.getAllByCreatedAtBetweenOrderByCreatedAtDesc(request.getFrom(), request.getTo());
        return entities.stream().map(x ->  new TelemetryTickDto()
                .setIsDeviceEnabled(x.isOn())
                .setRoomId(x.getRoomId())
                .setDeviceId(x.getDeviceId())
                .setCurrentTemperature(x.getCurrentTemperature())
                .setCreatedAt(x.getCreatedAt()))
                .toList();
    }
}
