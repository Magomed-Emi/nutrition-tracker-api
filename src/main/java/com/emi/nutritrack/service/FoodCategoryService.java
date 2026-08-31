package com.emi.nutritrack.service;


import com.emi.nutritrack.entity.FoodCategory;
import com.emi.nutritrack.repository.FoodCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodCategoryService {

    public final FoodCategoryRepository foodCategoryRepository;


    public FoodCategoryService(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }


    public void addCategory(FoodCategory foodCategory)
    {
        foodCategoryRepository.save(foodCategory);
    }

    public List<FoodCategory> getAllCategories()
    {
        return foodCategoryRepository.findAll();
    }

    public Optional<FoodCategory> getCategoryById(Long id)
    {
        return foodCategoryRepository.findById(id);
    }

    public void deleteCategory(Long id)
    {
        foodCategoryRepository.deleteById(id);
    }

    public void updateCategory(FoodCategory foodCategory, Long id)
    {
        Optional<FoodCategory> existe = foodCategoryRepository.findById(id);

        if (existe.isPresent())
        {
            FoodCategory categoryExistante = existe.get();

            categoryExistante.setName(foodCategory.getName());
            categoryExistante.setDescription(foodCategory.getDescription());

            foodCategoryRepository.save(categoryExistante);
        }
    }


}
