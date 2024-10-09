package ru.practicum.temperaturemanager.repository;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "Room")
@Data
@Accessors(chain = true)
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "config_id", nullable = false)
    private ConfigEntity config;

    @OneToOne
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    @Column(name = "room_name", nullable = false)
    private String roomName;

    @Column(name = "is_device_enabled", nullable = false)
    private Boolean isDeviceEnabled;
}
