package ru.practicum.temperaturemanager.controller.response;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DeviceResponse {
    private Long id;
    private String name;
    private String description;
    private String serial;
}
