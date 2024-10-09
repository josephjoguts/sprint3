package ru.practicum.temperaturemanager.repository;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.practicum.temperaturemanager.repository.RoomEntity;

import java.util.List;

@Entity
@Table(name = "cfgtable")
@Data
@Accessors(chain = true)
public class ConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "config", cascade = CascadeType.ALL)
    private List<RoomEntity> rooms;

    @Column(name = "config_name", nullable = false)
    private String configName;

    @Column(name = "target_temperature")
    private float targetTemperature;
}