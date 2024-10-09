package ru.practicum.temperaturemanager.controller.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DeviceRequest {
    private String name;
    private String description;
    private String serial;
}
