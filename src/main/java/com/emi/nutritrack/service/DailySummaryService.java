
package com.emi.nutritrack.service;

import com.emi.nutritrack.dto.DailySummaryResponse;
import com.emi.nutritrack.entity.DailyGoal;
import com.emi.nutritrack.entity.DailyLog;
import com.emi.nutritrack.repository.DailyGoalRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DailySummaryService {

    private final DailyGoalRepository dailyGoalRepository;
    private final DailyLogService dailyLogService;

    public DailySummaryService(
            DailyGoalRepository dailyGoalRepository,
            DailyLogService dailyLogService) {

        this.dailyGoalRepository = dailyGoalRepository;
        this.dailyLogService = dailyLogService;
    }

    public DailySummaryResponse getDailySummary(
            Long userId,
            LocalDate date) {

        // Récupérer les objectifs journaliers
        DailyGoal dailyGoal = dailyGoalRepository.findByUserIdAndDate(userId, date)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "DailyGoal introuvable"
                        )
                );

        // Récupérer la consommation du jour
        // si aucun repas n'a été consommé, les totaux restent à 0
        DailyLog dailyLog = dailyLogService
                .getDailyLogByUserAndDate(userId, date)
                .orElse(null);

        double consumedCalories = dailyLog != null
                ? dailyLog.getTotalCalories() : 0;

        double consumedProtein = dailyLog != null
                ? dailyLog.getTotalProtein() : 0;

        double consumedCarbs = dailyLog != null
                ? dailyLog.getTotalCarbs() : 0;

        double consumedFat = dailyLog != null
                ? dailyLog.getTotalFat() : 0;

        // Construire notre DTO
        DailySummaryResponse response =
                new DailySummaryResponse();

        response.setUserId(userId);
        response.setDate(date);

        // Calories
        response.setTargetCalories(
                dailyGoal.getTargetCalories());

        response.setConsumedCalories(consumedCalories);

        response.setRemainingCalories(
                dailyGoal.getTargetCalories() - consumedCalories);

        // Protéines
        response.setTargetProtein(
                dailyGoal.getTargetProtein());

        response.setConsumedProtein(consumedProtein);

        response.setRemainingProtein(
                dailyGoal.getTargetProtein() - consumedProtein);

        // Glucides
        response.setTargetCarbs(
                dailyGoal.getTargetCarbs());

        response.setConsumedCarbs(consumedCarbs);

        response.setRemainingCarbs(
                dailyGoal.getTargetCarbs() - consumedCarbs);

        // Lipides
        response.setTargetFat(
                dailyGoal.getTargetFat());

        response.setConsumedFat(consumedFat);

        response.setRemainingFat(
                dailyGoal.getTargetFat() - consumedFat);

        return response;
    }
}
