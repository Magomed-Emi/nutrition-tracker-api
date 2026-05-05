package com.emi.nutritrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.nutritrack.entity.DailyGoal;

public interface DailyGoalRepository extends JpaRepository<DailyGoal, Long>
{

}
