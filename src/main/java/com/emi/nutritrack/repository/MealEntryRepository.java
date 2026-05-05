package com.emi.nutritrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.nutritrack.entity.MealEntry;

public interface MealEntryRepository extends JpaRepository<MealEntry,Long>{
}