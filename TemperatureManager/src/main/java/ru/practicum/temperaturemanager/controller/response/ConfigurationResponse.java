package ru.practicum.temperaturemanager.controller.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ConfigurationResponse {
    private List<RoomResponse> rooms;
    private String configName;
    private float targetTemperature;
    private Long id;
}
