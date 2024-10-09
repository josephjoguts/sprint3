package ru.practicum.temperaturemonitor.repository;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Entity
@Table(name = "telemetry")
@Data
@Accessors(chain = true)
public class TelemetryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "current_temperature")
    private float currentTemperature;

    @Column(name = "is_on")
    private boolean isOn;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
