package com.emi.nutritrack.repository;

import com.emi.nutritrack.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FoodRepository extends JpaRepository<Food, Long> {
}