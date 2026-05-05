package com.emi.nutritrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.nutritrack.entity.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long>{

}