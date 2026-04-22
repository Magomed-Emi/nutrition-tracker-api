package com.emi.nutritrack.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "daily_goals")
public class DailyGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int targetCalories;

    @Column(nullable = false)
    private int targetProtein;

    @Column(nullable = false)
    private int targetCarbs;

    @Column(nullable = false)
    private int targetFat;

    @Column(nullable = true,length = 255)
    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    public DailyGoal(){}
    public DailyGoal(LocalDate date, int targetCalories, int targetProtein, int targetCarbs, int targetFat, String note) {
        this.date = date;
        this.targetCalories = targetCalories;
        this.targetProtein = targetProtein;
        this.targetCarbs = targetCarbs;
        this.targetFat = targetFat;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTargetCalories() {
        return targetCalories;
    }

    public int getTargetCarbs() {
        return targetCarbs;
    }

    public int getTargetFat() {
        return targetFat;
    }

    public int getTargetProtein() {
        return targetProtein;
    }

    public User getUser() {
        return user;
    }

    public String getNote() {
        return note;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTargetCalories(int targetCalories) {
        this.targetCalories = targetCalories;
    }

    public void setTargetCarbs(int targetCarbs) {
        this.targetCarbs = targetCarbs;
    }

    public void setTargetProtein(int targetProtein) {
        this.targetProtein = targetProtein;
    }

    public void setTargetFat(int targetFat) {
        this.targetFat = targetFat;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
