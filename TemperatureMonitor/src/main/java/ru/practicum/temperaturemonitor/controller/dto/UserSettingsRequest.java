package ru.practicum.temperaturemonitor.controller.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserSettingsRequest {
    private Date from;
    private Date to;
}
