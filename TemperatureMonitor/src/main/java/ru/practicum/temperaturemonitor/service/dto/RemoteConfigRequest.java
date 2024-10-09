package ru.practicum.temperaturemonitor.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class RemoteConfigRequest {
    private String configName;
    private float targetTemperature;
    private List<Room> rooms;

    @Data
    @Accessors(chain = true)
    public static class Room {
        private long id;
        private Device device;
        private String roomName;
        private Boolean isDeviceEnabled;
    }
    @Data
    @Accessors(chain = true)
    public static class Device {
        private long deviceId;
        private String name;
        private String description;
        private String serial;
    }
}
