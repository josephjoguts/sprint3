package ru.practicum.temperaturemonitor.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.temperaturemonitor.controller.dto.TelemetryRequest;
import ru.practicum.temperaturemonitor.service.HardwareServiceMock;
import ru.practicum.temperaturemonitor.service.SystemMonitorService;
import ru.practicum.temperaturemonitor.service.dto.RemoteConfigRequest;
import ru.practicum.temperaturemonitor.service.dto.RoomHardware;

import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class SystemSettingController {
    private final HardwareServiceMock hardwareService;
    private final SystemMonitorService systemMonitorService;

    @PostMapping("config")
    public ResponseEntity<String> addConfig(@RequestBody RemoteConfigRequest request) {
        List<RoomHardware> roomHardwares = request.getRooms().stream().map(
                x -> new RoomHardware()
                        .setDeviceId(x.getDevice().getDeviceId())
                        .setCurrentTemperature(new Random().nextFloat(15f, 25f))
                        .setIsDeviceEnabled(x.getIsDeviceEnabled())
                        .setTargetTemperature(request.getTargetTemperature()).setId(x.getId())
        ).toList();
        hardwareService.addNewHardware(roomHardwares);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("telemetry")
    @Deprecated
    public ResponseEntity<Void> setTelemetryData(@RequestBody TelemetryRequest telemetryRequest) {
        systemMonitorService.addTelemetry(telemetryRequest);
        return ResponseEntity.ok(null);
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from temperature monitor");
    }
}
