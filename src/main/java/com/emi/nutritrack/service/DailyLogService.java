package com.emi.nutritrack.service;

import com.emi.nutritrack.entity.DailyLog;
import com.emi.nutritrack.entity.MealEntry;
import com.emi.nutritrack.repository.DailyLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DailyLogService {

    private final DailyLogRepository dailyLogRepository;
    private final MealEntryService mealEntryService;

    public DailyLogService(DailyLogRepository dailyLogRepository, MealEntryService mealEntryService) {
        this.dailyLogRepository = dailyLogRepository;
        this.mealEntryService = mealEntryService;
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

    public double calculateTotalCalories(List<MealEntry> mealEntries)
    {
        double totalCalories = 0;

        for (MealEntry mealEntry : mealEntries)
        {
            totalCalories = totalCalories + mealEntryService.calculateCalories(mealEntry);
        }

        return totalCalories;
    }

    public double calculateTotalProtein(List<MealEntry> mealEntries)
    {
        double totalProtein = 0;

        for (MealEntry mealEntry : mealEntries)
        {
            totalProtein = totalProtein + mealEntryService.calculateProtein(mealEntry);
        }

        return totalProtein;
    }

    public double calculateTotalCarbs(List<MealEntry> mealEntries)
    {
        double totalCarbs = 0;

        for (MealEntry mealEntry : mealEntries)
        {
            totalCarbs = totalCarbs + mealEntryService.calculateCarbs(mealEntry);
        }

        return totalCarbs;
    }

    public double calculateTotalFat(List<MealEntry> mealEntries)
    {
        double totalFat = 0;

        for (MealEntry mealEntry : mealEntries)
        {
            totalFat = totalFat + mealEntryService.calculateFat(mealEntry);
        }

        return totalFat;
    }

    public void calculateDailyLog(DailyLog dailyLog)
    {
        List<MealEntry> mealEntries =
                mealEntryService.getMealEntriesByDate(
                        dailyLog.getUser().getId(),
                        dailyLog.getDate()
                );

        dailyLog.setTotalCalories(calculateTotalCalories(mealEntries));
        dailyLog.setTotalProtein(calculateTotalProtein(mealEntries));
        dailyLog.setTotalCarbs(calculateTotalCarbs(mealEntries));
        dailyLog.setTotalFat(calculateTotalFat(mealEntries));

        dailyLogRepository.save(dailyLog);
    }
}