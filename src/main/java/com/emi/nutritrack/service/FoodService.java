package com.emi.nutritrack.service;


import com.emi.nutritrack.entity.Food;
import com.emi.nutritrack.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository)
    {
        this.foodRepository = foodRepository;
    }

    public void addFood(Food food)
    {
        foodRepository.save(food);
    }
    public void deleteFood(Food food)
    {
        foodRepository.delete(food);
    }
    public List<Food> getAllFoods()
    {
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Long id)
    {
        return foodRepository.findById(id);
    }


    /*addFood
    getAllFoods
            getFoodById
    deleteFood
            updateFood
    getFoodByName (bonus)*/


}
