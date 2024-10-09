package ru.practicum.temperaturemonitor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.temperaturemonitor.controller.dto.UserSettingResponse;
import ru.practicum.temperaturemonitor.controller.dto.UserSettingsRequest;
import ru.practicum.temperaturemonitor.service.UserSettingsService;
import ru.practicum.temperaturemonitor.service.dto.TelemetryTickDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserSettingsController {
    private final UserSettingsService service;

    @GetMapping("config")
    public ResponseEntity<UserSettingResponse> getConfig(@RequestBody UserSettingsRequest request) {
        List<TelemetryTickDto> telemetry = service.getTelemetry(request);
        return ResponseEntity.ok(new UserSettingResponse().setTelemetryTicks(telemetry));
    }
}
