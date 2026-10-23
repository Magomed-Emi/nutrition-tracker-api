
package com.emi.nutritrack.service;

import com.emi.nutritrack.dto.DailySummaryResponse;
import com.emi.nutritrack.entity.DailyGoal;
import com.emi.nutritrack.entity.DailyLog;
import com.emi.nutritrack.repository.DailyGoalRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DailySummaryServiceTest {

    @Mock
    private DailyGoalRepository dailyGoalRepository;

    @Mock
    private DailyLogService dailyLogService;

    @InjectMocks
    private DailySummaryService dailySummaryService;

    private final Long userId = 1L;
    private final LocalDate date = LocalDate.of(2026, 10, 23);

    // Créer un objectif nutritionnel de test
    private DailyGoal createDailyGoal() {
        DailyGoal goal = new DailyGoal();
        goal.setTargetCalories(2200);
        goal.setTargetProtein(180);
        goal.setTargetCarbs(220);
        goal.setTargetFat(70);
        return goal;
    }

    // TEST 1 : Calculer les valeurs restantes
    @Test
    void calculateRemainingValuesTest() {

        DailyGoal goal = createDailyGoal();

        DailyLog log = new DailyLog();
        log.setTotalCalories(1500);
        log.setTotalProtein(120);
        log.setTotalCarbs(150);
        log.setTotalFat(40);

        when(dailyGoalRepository.findByUserIdAndDate(userId, date))
                .thenReturn(Optional.of(goal));

        when(dailyLogService.getDailyLogByUserAndDate(userId, date))
                .thenReturn(Optional.of(log));

        DailySummaryResponse result =
                dailySummaryService.getDailySummary(userId, date);

        assertEquals(700, result.getRemainingCalories());
        assertEquals(60, result.getRemainingProtein());
        assertEquals(70, result.getRemainingCarbs());
        assertEquals(30, result.getRemainingFat());
    }

    // TEST 2 : Aucun repas consommé
    @Test
    void noMealConsumedTest() {

        DailyGoal goal = createDailyGoal();

        when(dailyGoalRepository.findByUserIdAndDate(userId, date))
                .thenReturn(Optional.of(goal));

        when(dailyLogService.getDailyLogByUserAndDate(userId, date))
                .thenReturn(Optional.empty());

        DailySummaryResponse result =
                dailySummaryService.getDailySummary(userId, date);

        assertEquals(0, result.getConsumedCalories());
        assertEquals(0, result.getConsumedProtein());
        assertEquals(2200, result.getRemainingCalories());
        assertEquals(180, result.getRemainingProtein());
    }

    // TEST 3 : Objectif introuvable
    @Test
    void dailyGoalNotFoundTest() {

        when(dailyGoalRepository.findByUserIdAndDate(userId, date))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                dailySummaryService.getDailySummary(userId, date));

        verifyNoInteractions(dailyLogService);
    }

    // TEST 4 : Vérifier utilisateur et date
    @Test
    void correctUserAndDateTest() {

        DailyGoal goal = createDailyGoal();

        when(dailyGoalRepository.findByUserIdAndDate(userId, date))
                .thenReturn(Optional.of(goal));

        when(dailyLogService.getDailyLogByUserAndDate(userId, date))
                .thenReturn(Optional.empty());

        DailySummaryResponse result =
                dailySummaryService.getDailySummary(userId, date);

        assertEquals(userId, result.getUserId());
        assertEquals(date, result.getDate());

        verify(dailyGoalRepository)
                .findByUserIdAndDate(userId, date);

        verify(dailyLogService)
                .getDailyLogByUserAndDate(userId, date);
    }
}
