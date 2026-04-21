package com.emi.nutritrack.entity;


import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class MealEntry {

    private Long id;
    private int quantity;
    private String mealType;
    private date consumedAt;
    private String user;
    private String food;

    /*



       id
       quantity
       mealType
       consumedAt
       user
       food



     */
}
