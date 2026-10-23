
package com.emi.nutritrack.dto;

import java.time.LocalDate;

public class DailySummaryResponse {

    private Long userId;
    private LocalDate date;

    // Calories
    private double targetCalories;
    private double consumedCalories;
    private double remainingCalories;

    // Protéines
    private double targetProtein;
    private double consumedProtein;
    private double remainingProtein;

    // Glucides
    private double targetCarbs;
    private double consumedCarbs;
    private double remainingCarbs;

    // Lipides
    private double targetFat;
    private double consumedFat;
    private double remainingFat;

    public DailySummaryResponse() {
    }

    // Getters et Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(double targetCalories) {
        this.targetCalories = targetCalories;
    }

    public double getConsumedCalories() {
        return consumedCalories;
    }

    public void setConsumedCalories(double consumedCalories) {
        this.consumedCalories = consumedCalories;
    }

    public double getRemainingCalories() {
        return remainingCalories;
    }

    public void setRemainingCalories(double remainingCalories) {
        this.remainingCalories = remainingCalories;
    }

    public double getTargetProtein() {
        return targetProtein;
    }

    public void setTargetProtein(double targetProtein) {
        this.targetProtein = targetProtein;
    }

    public double getConsumedProtein() {
        return consumedProtein;
    }

    public void setConsumedProtein(double consumedProtein) {
        this.consumedProtein = consumedProtein;
    }

    public double getRemainingProtein() {
        return remainingProtein;
    }

    public void setRemainingProtein(double remainingProtein) {
        this.remainingProtein = remainingProtein;
    }

    public double getTargetCarbs() {
        return targetCarbs;
    }

    public void setTargetCarbs(double targetCarbs) {
        this.targetCarbs = targetCarbs;
    }

    public double getConsumedCarbs() {
        return consumedCarbs;
    }

    public void setConsumedCarbs(double consumedCarbs) {
        this.consumedCarbs = consumedCarbs;
    }

    public double getRemainingCarbs() {
        return remainingCarbs;
    }

    public void setRemainingCarbs(double remainingCarbs) {
        this.remainingCarbs = remainingCarbs;
    }

    public double getTargetFat() {
        return targetFat;
    }

    public void setTargetFat(double targetFat) {
        this.targetFat = targetFat;
    }

    public double getConsumedFat() {
        return consumedFat;
    }

    public void setConsumedFat(double consumedFat) {
        this.consumedFat = consumedFat;
    }

    public double getRemainingFat() {
        return remainingFat;
    }

    public void setRemainingFat(double remainingFat) {
        this.remainingFat = remainingFat;
    }
}
