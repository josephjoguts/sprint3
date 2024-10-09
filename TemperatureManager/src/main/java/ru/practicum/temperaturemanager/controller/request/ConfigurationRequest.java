package ru.practicum.temperaturemanager.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ConfigurationRequest {
    private long configId;
    private String configName;
    private float targetTemperature;
    private List<RoomRequest> rooms;
}
