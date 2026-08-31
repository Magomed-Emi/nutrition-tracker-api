package com.emi.nutritrack.service;

import com.emi.nutritrack.entity.DailyGoal;
import com.emi.nutritrack.entity.DailyLog;
import com.emi.nutritrack.repository.DailyGoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DailyGoalService {

    private final DailyGoalRepository dailyGoalRepository;

    public DailyGoalService(DailyGoalRepository dailyGoalRepository)
    {
        this.dailyGoalRepository = dailyGoalRepository;
    }

    public void addDailyGoal(DailyGoal dailyGoal)
    {
        dailyGoalRepository.save(dailyGoal);
    }

    public List<DailyGoal> getAllDailyGoals()
    {
        return dailyGoalRepository.findAll();
    }

    public Optional<DailyGoal> getDailyGoalById(Long id)
    {
        return dailyGoalRepository.findById(id);
    }

    public void deleteDailyGoal(Long id)
    {
        dailyGoalRepository.deleteById(id);
    }

    public void updateDailyGoal(DailyGoal dailyGoal, Long id)
    {
        Optional<DailyGoal> existe = dailyGoalRepository.findById(id);

        if (existe.isPresent())
        {
            DailyGoal dailyGoalExistant = existe.get();

            dailyGoalExistant.setDate(dailyGoal.getDate());
            dailyGoalExistant.setNote(dailyGoal.getNote());
            dailyGoalExistant.setUser(dailyGoal.getUser());
            dailyGoalExistant.setTargetCalories(dailyGoal.getTargetCalories());
            dailyGoalExistant.setTargetCarbs(dailyGoal.getTargetCarbs());
            dailyGoalExistant.setTargetFat(dailyGoal.getTargetFat());
            dailyGoalExistant.setTargetProtein(dailyGoal.getTargetProtein());

            dailyGoalRepository.save(dailyGoalExistant);
        }
    }

    public double calculateRemainingCaloriesGoal(DailyLog dailyLog, DailyGoal dailyGoal)
    {
        return dailyGoal.getTargetCalories() - dailyLog.getTotalCalories();
    }

    public double calculateRemainingProteinGoal(DailyLog dailyLog, DailyGoal dailyGoal)
    {
        return dailyGoal.getTargetProtein() - dailyLog.getTotalProtein();
    }

    public double calculateRemainingCarbsGoal(DailyLog dailyLog, DailyGoal dailyGoal)
    {
        return dailyGoal.getTargetCarbs() - dailyLog.getTotalCarbs();
    }

    public double calculateRemainingFatGoal(DailyLog dailyLog, DailyGoal dailyGoal)
    {
        return dailyGoal.getTargetFat() - dailyLog.getTotalFat();
    }

    /*
        calculateCaloriesGoal(...)
        calculateProteinGoal(...)
        calculateCarbsGoal(...)
        calculateFatGoal(...)
    */
}