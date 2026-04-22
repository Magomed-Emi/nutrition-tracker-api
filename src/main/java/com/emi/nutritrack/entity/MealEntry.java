package com.emi.nutritrack.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meal_entries")
public class MealEntry {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MealType mealType;

    @Column(nullable = false)
    private LocalDateTime consumedAt;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    public MealEntry(){}

    public MealEntry(int quantity, MealType mealType, LocalDateTime consumedAt, User user, Food food)    {
        this.quantity = quantity;
        this.mealType = mealType;
        this.consumedAt = consumedAt;
        this.user = user;
        this.food = food;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public MealType getMealType()
    {
        return mealType;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getConsumedAt() {
        return consumedAt;
    }

    public Food getFood() {
        return food;
    }

    public void setConsumedAt(LocalDateTime consumedAt) {
        this.consumedAt = consumedAt;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMealType(MealType mealType)
    {
        this.mealType = mealType;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
