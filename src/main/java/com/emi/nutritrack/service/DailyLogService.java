package com.emi.nutritrack.service;

import com.emi.nutritrack.entity.DailyLog;
import com.emi.nutritrack.entity.MealEntry;
import com.emi.nutritrack.repository.DailyLogRepository;
import com.emi.nutritrack.repository.MealEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class DailyLogService {

    private final DailyLogRepository dailyLogRepository;
    private final MealEntryRepository mealEntryRepository;

    public DailyLogService(
            DailyLogRepository dailyLogRepository,
            MealEntryRepository mealEntryRepository) {

        this.dailyLogRepository = dailyLogRepository;
        this.mealEntryRepository = mealEntryRepository;
    }


    public void addDailyLog(DailyLog dailyLog) {
        dailyLogRepository.save(dailyLog);
    }


    public List<DailyLog> getAllDailyLogs() {
        return dailyLogRepository.findAll();
    }


    public Optional<DailyLog> getDailyLogById(Long id) {
        return dailyLogRepository.findById(id);
    }


    public void deleteDailyLog(Long id) {
        dailyLogRepository.deleteById(id);
    }


    public void updateDailyLog(DailyLog dailyLog, Long id) {

        Optional<DailyLog> existe = dailyLogRepository.findById(id);

        if (existe.isPresent()) {

            DailyLog dailyLogExistant = existe.get();

            dailyLogExistant.setDate(dailyLog.getDate());
            dailyLogExistant.setTotalCalories(dailyLog.getTotalCalories());
            dailyLogExistant.setTotalProtein(dailyLog.getTotalProtein());
            dailyLogExistant.setTotalCarbs(dailyLog.getTotalCarbs());
            dailyLogExistant.setTotalFat(dailyLog.getTotalFat());
            dailyLogExistant.setUser(dailyLog.getUser());

            dailyLogRepository.save(dailyLogExistant);
        }
    }


    public List<MealEntry> getMealEntriesByDate(
            Long userId,
            LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        return mealEntryRepository.findByUserIdAndConsumedAtBetween(
                userId,
                start,
                end
        );
    }


    public double calculateTotalCalories(List<MealEntry> mealEntries) {

        double totalCalories = 0;

        for (MealEntry mealEntry : mealEntries) {

            double calories = mealEntry.getFood().getCalories();
            double quantityReference =
                    mealEntry.getFood().getQuantityReference();

            double quantity = mealEntry.getQuantity();

            totalCalories +=
                    calories * (quantity / quantityReference);
        }

        return totalCalories;
    }


    public double calculateTotalProtein(List<MealEntry> mealEntries) {

        double totalProtein = 0;

        for (MealEntry mealEntry : mealEntries) {

            double protein = mealEntry.getFood().getProtein();
            double quantityReference =
                    mealEntry.getFood().getQuantityReference();

            double quantity = mealEntry.getQuantity();

            totalProtein +=
                    protein * (quantity / quantityReference);
        }

        return totalProtein;
    }


    public double calculateTotalCarbs(List<MealEntry> mealEntries) {

        double totalCarbs = 0;

        for (MealEntry mealEntry : mealEntries) {

            double carbs = mealEntry.getFood().getCarbs();
            double quantityReference =
                    mealEntry.getFood().getQuantityReference();

            double quantity = mealEntry.getQuantity();

            totalCarbs +=
                    carbs * (quantity / quantityReference);
        }

        return totalCarbs;
    }


    public double calculateTotalFat(List<MealEntry> mealEntries) {

        double totalFat = 0;

        for (MealEntry mealEntry : mealEntries) {

            double fat = mealEntry.getFood().getFat();
            double quantityReference =
                    mealEntry.getFood().getQuantityReference();

            double quantity = mealEntry.getQuantity();

            totalFat +=
                    fat * (quantity / quantityReference);
        }

        return totalFat;
    }


    public void calculateDailyLog(DailyLog dailyLog) {

        List<MealEntry> mealEntries =
                getMealEntriesByDate(
                        dailyLog.getUser().getId(),
                        dailyLog.getDate()
                );

        dailyLog.setTotalCalories(
                calculateTotalCalories(mealEntries));

        dailyLog.setTotalProtein(
                calculateTotalProtein(mealEntries));

        dailyLog.setTotalCarbs(
                calculateTotalCarbs(mealEntries));

        dailyLog.setTotalFat(
                calculateTotalFat(mealEntries));

        dailyLogRepository.save(dailyLog);
    }


    public Optional<DailyLog> getDailyLogByUserAndDate(
            Long userId,
            LocalDate dateLog) {

        return dailyLogRepository
                .findByUserIdAndDate(userId, dateLog);
    }
}