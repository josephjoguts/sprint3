package ru.practicum.temperaturemanager.repository;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.practicum.temperaturemanager.repository.RoomEntity;

@Entity
@Table(name = "Device")
@Data
@Accessors(chain = true)
public class DeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "device")
    private RoomEntity room;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "serial")
    private String serial;
}
