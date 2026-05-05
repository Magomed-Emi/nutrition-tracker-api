package com.emi.nutritrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.nutritrack.entity.DailyLog;

public interface DailyLogRepository extends JpaRepository<DailyLog, Long>{
}