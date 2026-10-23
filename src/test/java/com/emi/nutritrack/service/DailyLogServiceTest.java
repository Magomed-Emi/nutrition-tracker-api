
package com.emi.nutritrack.service;

import com.emi.nutritrack.entity.DailyLog;
import com.emi.nutritrack.entity.Food;
import com.emi.nutritrack.entity.MealEntry;
import com.emi.nutritrack.entity.User;
import com.emi.nutritrack.repository.DailyLogRepository;
import com.emi.nutritrack.repository.MealEntryRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DailyLogServiceTest {

    @Mock
    private DailyLogRepository dailyLogRepository;

    @Mock
    private MealEntryRepository mealEntryRepository;

    @InjectMocks
    private DailyLogService dailyLogService;


    // =========================
    // CALCUL CALORIES
    // =========================

    @Test
    void calculateTotalCaloriesTest() {

        Food food1 = new Food();
        food1.setCalories(165);
        food1.setQuantityReference(100);

        MealEntry mealEntry1 = new MealEntry();
        mealEntry1.setFood(food1);
        mealEntry1.setQuantity(200);

        Food food2 = new Food();
        food2.setCalories(200);
        food2.setQuantityReference(100);

        MealEntry mealEntry2 = new MealEntry();
        mealEntry2.setFood(food2);
        mealEntry2.setQuantity(100);

        List<MealEntry> mealEntries =
                List.of(mealEntry1, mealEntry2);

        double result =
                dailyLogService.calculateTotalCalories(mealEntries);

        assertEquals(530.0, result);
    }


    // =========================
    // CALCUL PROTEINES
    // =========================

    @Test
    void calculateTotalProteinTest() {

        Food food1 = new Food();
        food1.setProtein(31);
        food1.setQuantityReference(100);

        MealEntry mealEntry1 = new MealEntry();
        mealEntry1.setFood(food1);
        mealEntry1.setQuantity(200);

        Food food2 = new Food();
        food2.setProtein(20);
        food2.setQuantityReference(100);

        MealEntry mealEntry2 = new MealEntry();
        mealEntry2.setFood(food2);
        mealEntry2.setQuantity(100);

        List<MealEntry> mealEntries =
                List.of(mealEntry1, mealEntry2);

        double result =
                dailyLogService.calculateTotalProtein(mealEntries);

        assertEquals(82.0, result);
    }


    // =========================
    // CALCUL GLUCIDES
    // =========================

    @Test
    void calculateTotalCarbsTest() {

        Food food1 = new Food();
        food1.setCarbs(20);
        food1.setQuantityReference(100);

        MealEntry mealEntry1 = new MealEntry();
        mealEntry1.setFood(food1);
        mealEntry1.setQuantity(200);

        Food food2 = new Food();
        food2.setCarbs(50);
        food2.setQuantityReference(100);

        MealEntry mealEntry2 = new MealEntry();
        mealEntry2.setFood(food2);
        mealEntry2.setQuantity(100);

        List<MealEntry> mealEntries =
                List.of(mealEntry1, mealEntry2);

        double result =
                dailyLogService.calculateTotalCarbs(mealEntries);

        assertEquals(90.0, result);
    }


    // =========================
    // CALCUL LIPIDES
    // =========================

    @Test
    void calculateTotalFatTest() {

        Food food1 = new Food();
        food1.setFat(4);
        food1.setQuantityReference(100);

        MealEntry mealEntry1 = new MealEntry();
        mealEntry1.setFood(food1);
        mealEntry1.setQuantity(200);

        Food food2 = new Food();
        food2.setFat(12);
        food2.setQuantityReference(100);

        MealEntry mealEntry2 = new MealEntry();
        mealEntry2.setFood(food2);
        mealEntry2.setQuantity(100);

        List<MealEntry> mealEntries =
                List.of(mealEntry1, mealEntry2);

        double result =
                dailyLogService.calculateTotalFat(mealEntries);

        assertEquals(20.0, result);
    }


    // =========================
    // CALCUL DU DAILY LOG COMPLET
    // =========================

    @Test
    void calculateDailyLogTest() {

        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);

        LocalDate date = LocalDate.of(2026, 10, 10);

        DailyLog dailyLog = new DailyLog();
        dailyLog.setUser(user);
        dailyLog.setDate(date);

        Food food = new Food();
        food.setCalories(165);
        food.setProtein(31);
        food.setCarbs(20);
        food.setFat(4);
        food.setQuantityReference(100);

        MealEntry mealEntry = new MealEntry();
        mealEntry.setFood(food);
        mealEntry.setQuantity(200);

        List<MealEntry> mealEntries = List.of(mealEntry);

        when(mealEntryRepository.findByUserIdAndConsumedAtBetween(
                eq(1L),
                any(LocalDateTime.class),
                any(LocalDateTime.class)
        )).thenReturn(mealEntries);

        dailyLogService.calculateDailyLog(dailyLog);

        assertEquals(330.0, dailyLog.getTotalCalories());
        assertEquals(62.0, dailyLog.getTotalProtein());
        assertEquals(40.0, dailyLog.getTotalCarbs());
        assertEquals(8.0, dailyLog.getTotalFat());

        verify(dailyLogRepository).save(dailyLog);
    }


    // =========================
    // AJOUT DAILY LOG
    // =========================

    @Test
    void addDailyLogTest() {

        DailyLog dailyLog = new DailyLog();

        dailyLogService.addDailyLog(dailyLog);

        verify(dailyLogRepository).save(dailyLog);
    }


    // =========================
    // GET ALL
    // =========================

    @Test
    void getAllDailyLogsTest() {

        DailyLog dailyLog1 = new DailyLog();
        DailyLog dailyLog2 = new DailyLog();

        List<DailyLog> expected =
                List.of(dailyLog1, dailyLog2);

        when(dailyLogRepository.findAll())
                .thenReturn(expected);

        List<DailyLog> result =
                dailyLogService.getAllDailyLogs();

        assertEquals(expected, result);
    }


    // =========================
    // GET BY ID
    // =========================

    @Test
    void getDailyLogByIdTest() {

        DailyLog dailyLog = new DailyLog();

        when(dailyLogRepository.findById(1L))
                .thenReturn(Optional.of(dailyLog));

        Optional<DailyLog> result =
                dailyLogService.getDailyLogById(1L);

        assertEquals(dailyLog, result.get());
    }


    // =========================
    // DELETE
    // =========================

    @Test
    void deleteDailyLogTest() {

        dailyLogService.deleteDailyLog(1L);

        verify(dailyLogRepository).deleteById(1L);
    }


    // =========================
    // GET PAR USER + DATE
    // =========================

    @Test
    void getDailyLogByUserAndDateTest() {

        Long userId = 1L;

        LocalDate date = LocalDate.of(2026, 10, 10);

        DailyLog dailyLog = new DailyLog();

        when(dailyLogRepository.findByUserIdAndDate(userId, date))
                .thenReturn(Optional.of(dailyLog));

        Optional<DailyLog> result =
                dailyLogService.getDailyLogByUserAndDate(
                        userId,
                        date
                );

        assertEquals(dailyLog, result.get());
    }
}
