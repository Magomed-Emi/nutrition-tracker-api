package com.emi.nutritrack.entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class DailyGoal {


    private Long id;
    private LocalDate date;
    private int targetCalories;
    private int targetProtein;
    private int targetCarbs;
    private int targetFat;
    private int TargetIntake;
    private String note;
    private String user;

}
