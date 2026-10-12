package com.emi.nutritrack.service;

import com.emi.nutritrack.entity.Food;
import com.emi.nutritrack.entity.MealEntry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealEntryServiceTest {

    MealEntryService mealEntryService =
            new MealEntryService(null, null, null, null);


    @Test
    void calculateCaloriesTest() {

        Food food = new Food();
        food.setCalories(165);
        food.setQuantityReference(100);

        MealEntry mealEntry = new MealEntry();
        mealEntry.setFood(food);
        mealEntry.setQuantity(200);

        double result =
                mealEntryService.calculateCalories(mealEntry);

        assertEquals(330, result);
    }


    @Test
    void calculateProtein() {

        Food food = new Food();
        food.setProtein(31);
        food.setQuantityReference(100);

        MealEntry mealEntry = new MealEntry();
        mealEntry.setFood(food);
        mealEntry.setQuantity(200);

        double result =
                mealEntryService.calculateProtein(mealEntry);

        assertEquals(62, result);
    }


    @Test
    void calculateCarbsTest() {

        Food food = new Food();
        food.setCarbs(20);
        food.setQuantityReference(100);

        MealEntry mealEntry = new MealEntry();
        mealEntry.setFood(food);
        mealEntry.setQuantity(200);

        double result =
                mealEntryService.calculateCarbs(mealEntry);

        assertEquals(40, result);
    }


    @Test
    void calculateFatTest() {

        Food food = new Food();
        food.setFat(4);
        food.setQuantityReference(100);

        MealEntry mealEntry = new MealEntry();
        mealEntry.setFood(food);
        mealEntry.setQuantity(200);

        double result =
                mealEntryService.calculateFat(mealEntry);

        assertEquals(8, result);
    }
}