package com.emi.nutritrack.repository;

import com.emi.nutritrack.entity.MealEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MealEntryRepository extends JpaRepository<MealEntry, Long> {

    List<MealEntry> findByUserIdAndConsumedAtBetween(
            Long userId,
            LocalDateTime start,
            LocalDateTime end
    );
}