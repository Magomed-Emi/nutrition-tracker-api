package com.emi.nutritrack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Foods") 
public class Food {



    @GeneratedValue()
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = true)
    private int calories;

    @Column(nullable = true )
    private int protein;

    @Column(nullable = true)
    private int carbs;

    @Column(nullable = true)
    private int fat;

    @Column(nullable = false)
    private int quantityReference;

    @Column(nullable = false)
    private int unit;

    @Column(nullable = false)
    private String foodCategory;


    public Food(){}
    public Food(String name, int protein, int calories, int carbs, int fat, int quantityReference, int unit, String foodCategory)
    {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.quantityReference = quantityReference;
        this.unit = unit;
        this.foodCategory = foodCategory;
    }

    public String getName() {
        return name;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCalories() {
        return calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getQuantityReference() {
        return quantityReference;
    }

    public int getUnit() {
        return unit;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public Long getId() {
        return id;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setQuantityReference(int quantityReference) {
        this.quantityReference = quantityReference;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
