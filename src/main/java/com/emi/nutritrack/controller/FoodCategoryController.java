package com.emi.nutritrack.controller;

import com.emi.nutritrack.entity.FoodCategory;
import com.emi.nutritrack.service.FoodCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class FoodCategoryController {

    private final FoodCategoryService foodCategoryService;
    public FoodCategoryController(FoodCategoryService foodCategoryService) {
        this.foodCategoryService = foodCategoryService;
    }

    @PostMapping("/ajouter")
    public void addCategory(@RequestBody FoodCategory foodCategory)
    {
        foodCategoryService.addCategory(foodCategory);
    }

    @GetMapping("/categories")
    public List<FoodCategory> getAllCategories()
    {
        return foodCategoryService.getAllCategories();
    }

    @GetMapping("/getCategoryId/{id}")
    public Optional<FoodCategory> getCategoryById(@PathVariable Long id)
    {
        return foodCategoryService.getCategoryById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id)
    {
        foodCategoryService.deleteCategory(id);
    }

    @PutMapping("/updateCategory/{id}")
    public void updateCategory(@RequestBody FoodCategory foodCategory, @PathVariable Long id )
    {
        foodCategoryService.updateCategory(foodCategory,id);
    }

}
