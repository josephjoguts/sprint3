package ru.practicum.temperaturemonitor.controller.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TelemetryRequest {
    private Long deviceId;
    private Float currentTemperature;
}
