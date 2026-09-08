package com.emi.nutritrack.service;

import com.emi.nutritrack.entity.DailyGoal;
import com.emi.nutritrack.entity.DailyLog;
import com.emi.nutritrack.repository.DailyGoalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DailyGoalService {

    private final DailyGoalRepository dailyGoalRepository;
    private final DailyLogService dailyLogService;

    public DailyGoalService( DailyGoalRepository dailyGoalRepository, DailyLogService dailyLogService)
    {
        this.dailyGoalRepository = dailyGoalRepository;
        this.dailyLogService = dailyLogService;
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


    public Optional<DailyGoal> getDailyGoalByUserAndDate(Long userId, LocalDate date)
    {
        return dailyGoalRepository.findByUserIdAndDate(userId,date);
    }

    public Map<String, Double> getRemainingGoalsByUserAndDate(Long userId, LocalDate date)
    {
        Optional<DailyGoal> dailyGoalOptional = getDailyGoalByUserAndDate(userId, date);

        Optional<DailyLog> dailyLogOptional = dailyLogService.getDailyLogByUserAndDate(userId, date);

        if (dailyGoalOptional.isPresent() && dailyLogOptional.isPresent())
        {
            DailyGoal dailyGoal = dailyGoalOptional.get();
            DailyLog dailyLog = dailyLogOptional.get();

            double remainingCalories = calculateRemainingCaloriesGoal(dailyLog, dailyGoal);

            double remainingProtein = calculateRemainingProteinGoal(dailyLog, dailyGoal);

            double remainingCarbs = calculateRemainingCarbsGoal(dailyLog, dailyGoal);

            double remainingFat = calculateRemainingFatGoal(dailyLog, dailyGoal);

            Map<String, Double> remainingGoals = new HashMap<>();

            remainingGoals.put("remainingCalories", remainingCalories);
            remainingGoals.put("remainingProtein", remainingProtein);
            remainingGoals.put("remainingCarbs", remainingCarbs);
            remainingGoals.put("remainingFat", remainingFat);

            return remainingGoals;
        }

        return new HashMap<>();
    }

}