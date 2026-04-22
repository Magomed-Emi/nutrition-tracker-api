package com.emi.nutritrack.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "food_categories")
public class FoodCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false, length = 250)
    private String description;

    @OneToMany(mappedBy = "foodCategory")
    private List<Food> foods;


    public FoodCategory(){}
    public FoodCategory(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}
