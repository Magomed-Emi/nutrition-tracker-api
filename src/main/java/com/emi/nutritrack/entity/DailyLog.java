package com.emi.nutritrack.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "daily_logs")
public class DailyLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int totalCalories;

    @Column(nullable = true)
    private int totalProtein;

    @Column(nullable = true)
    private int totalCarbs;

    @Column(nullable = true)
    private int totalFat;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    public DailyLog(){}
    public DailyLog(LocalDate date, int totalCalories, int totalCarbs, int totalProtein, int totalFat)
    {
        this.date = date;
        this.totalCalories = totalCalories;
        this.totalCarbs = totalCarbs;
        this.totalProtein = totalProtein;
        this.totalFat = totalFat;
    }

    public Long getId() {
        return id;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTotalCarbs() {
        return totalCarbs;
    }

    public int getTotalFat() {
        return totalFat;
    }

    public int getTotalProtein() {
        return totalProtein;
    }

    public User getUser() {
        return user;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public void setTotalCarbs(int totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public void setTotalFat(int totalFat) {
        this.totalFat = totalFat;
    }

    public void setTotalProtein(int totalProtein) {
        this.totalProtein = totalProtein;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
