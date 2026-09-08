package com.emi.nutritrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.nutritrack.entity.DailyGoal;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyGoalRepository extends JpaRepository<DailyGoal, Long>
{
    Optional<DailyGoal> findByUserIdAndDate(Long userId, LocalDate date);
}
