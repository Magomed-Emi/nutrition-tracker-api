package com.emi.nutritrack.service;

import com.emi.nutritrack.entity.Food;
import com.emi.nutritrack.entity.MealEntry;
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

    public void deleteFood(Long id)
    {
        foodRepository.deleteById(id);
    }

    public List<Food> getAllFoods()
    {
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Long id)
    {
        return foodRepository.findById(id);
    }
    public void updateFood(Food food, Long id)
    {
        Optional<Food> existe = foodRepository.findById(id);

        if (existe.isPresent())
        {
            Food foodExistant = existe.get();

            foodExistant.setName(food.getName());
            foodExistant.setCalories(food.getCalories());
            foodExistant.setProtein(food.getProtein());
            foodExistant.setCarbs(food.getCarbs());
            foodExistant.setFat(food.getFat());

            foodRepository.save(foodExistant);
        }
    }



}
