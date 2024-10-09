package ru.practicum.temperaturemanager.controller.response;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.practicum.temperaturemanager.repository.ConfigEntity;
import ru.practicum.temperaturemanager.repository.DeviceEntity;

@Data
@Accessors(chain = true)
public class RoomResponse {
    private Long id;
    private DeviceResponse device;
    private String roomName;
    private Boolean isDeviceEnabled;
}
