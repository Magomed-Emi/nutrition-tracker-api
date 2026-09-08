package com.emi.nutritrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.nutritrack.entity.DailyLog;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyLogRepository extends JpaRepository<DailyLog, Long>{
    Optional<DailyLog> findByUserIdAndDate(Long userId, LocalDate date);
}