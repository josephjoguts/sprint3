package ru.practicum.temperaturemonitor.controller.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.practicum.temperaturemonitor.service.dto.TelemetryTickDto;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserSettingResponse {
    List<TelemetryTickDto> telemetryTicks;
}
