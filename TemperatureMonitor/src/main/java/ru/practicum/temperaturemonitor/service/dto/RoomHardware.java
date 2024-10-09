package ru.practicum.temperaturemonitor.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoomHardware {
    private long id;
    private Boolean isDeviceEnabled;
    private Float targetTemperature;
    private Float currentTemperature;
    private long deviceId;
}
