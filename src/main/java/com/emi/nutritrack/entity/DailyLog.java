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
    private double totalCalories;

    @Column(nullable = true)
    private double totalProtein;

    @Column(nullable = true)
    private double totalCarbs;

    @Column(nullable = true)
    private double totalFat;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public DailyLog() {
    }

    public DailyLog(LocalDate date,
                    double totalCalories,
                    double totalCarbs,
                    double totalProtein,
                    double totalFat) {

        this.date = date;
        this.totalCalories = totalCalories;
        this.totalCarbs = totalCarbs;
        this.totalProtein = totalProtein;
        this.totalFat = totalFat;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public User getUser() {
        return user;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public void setTotalProtein(double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public void setTotalCarbs(double totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public void setUser(User user) {
        this.user = user;
    }
}