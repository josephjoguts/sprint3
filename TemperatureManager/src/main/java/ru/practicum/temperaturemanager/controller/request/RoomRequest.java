package ru.practicum.temperaturemanager.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoomRequest {
    private long roomId;
    private long deviceId;
    private String name;
    private Boolean isDeviceEnabled;
}
