package ru.practicum.temperaturemonitor.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class TelemetryTickDto {
    private long roomId;
    private long deviceId;
    private Boolean isDeviceEnabled;
    private Float currentTemperature;
    private Date createdAt;
}
