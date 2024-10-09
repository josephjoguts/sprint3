package ru.practicum.temperaturemonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MonitorRepository extends JpaRepository<TelemetryEntity, Long> {
    List<TelemetryEntity> getAllByCreatedAtBetweenOrderByCreatedAtDesc(Date from, Date to);
}
