package com.emi.nutritrack.service;

import com.emi.nutritrack.entity.DailyLog;
import com.emi.nutritrack.entity.MealEntry;
import com.emi.nutritrack.entity.User;
import com.emi.nutritrack.repository.DailyLogRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DailyLogServiceTest {

    @Mock
    private DailyLogRepository dailyLogRepository;

    @Mock
    private MealEntryService mealEntryService;

    @InjectMocks
    private DailyLogService dailyLogService;


    //Généré avec l'ia pour gagner du temps

    // =========================
    // CALCUL CALORIES
    // =========================

    @Test
    void calculateTotalCaloriesTest() {

        MealEntry mealEntry1 = new MealEntry();
        MealEntry mealEntry2 = new MealEntry();

        List<MealEntry> mealEntries =
                List.of(mealEntry1, mealEntry2);

        when(mealEntryService.calculateCalories(mealEntry1))
                .thenReturn(330.0);

        when(mealEntryService.calculateCalories(mealEntry2))
                .thenReturn(200.0);

        double result =
                dailyLogService.calculateTotalCalories(mealEntries);

        assertEquals(530.0, result);
    }


    // =========================
    // CALCUL PROTEINES
    // =========================

    @Test
    void calculateTotalProteinTest() {

        MealEntry mealEntry1 = new MealEntry();
        MealEntry mealEntry2 = new MealEntry();

        List<MealEntry> mealEntries =
                List.of(mealEntry1, mealEntry2);

        when(mealEntryService.calculateProtein(mealEntry1))
                .thenReturn(62.0);

        when(mealEntryService.calculateProtein(mealEntry2))
                .thenReturn(20.0);

        double result =
                dailyLogService.calculateTotalProtein(mealEntries);

        assertEquals(82.0, result);
    }


    // =========================
    // CALCUL GLUCIDES
    // =========================

    @Test
    void calculateTotalCarbsTest() {

        MealEntry mealEntry1 = new MealEntry();
        MealEntry mealEntry2 = new MealEntry();

        List<MealEntry> mealEntries =
                List.of(mealEntry1, mealEntry2);

        when(mealEntryService.calculateCarbs(mealEntry1))
                .thenReturn(40.0);

        when(mealEntryService.calculateCarbs(mealEntry2))
                .thenReturn(50.0);

        double result =
                dailyLogService.calculateTotalCarbs(mealEntries);

        assertEquals(90.0, result);
    }


    // =========================
    // CALCUL LIPIDES
    // =========================

    @Test
    void calculateTotalFatTest() {

        MealEntry mealEntry1 = new MealEntry();
        MealEntry mealEntry2 = new MealEntry();

        List<MealEntry> mealEntries =
                List.of(mealEntry1, mealEntry2);

        when(mealEntryService.calculateFat(mealEntry1))
                .thenReturn(8.0);

        when(mealEntryService.calculateFat(mealEntry2))
                .thenReturn(12.0);

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

        MealEntry mealEntry = new MealEntry();

        List<MealEntry> mealEntries =
                List.of(mealEntry);

        when(mealEntryService.getMealEntriesByDate(1L, date))
                .thenReturn(mealEntries);

        when(mealEntryService.calculateCalories(mealEntry))
                .thenReturn(330.0);

        when(mealEntryService.calculateProtein(mealEntry))
                .thenReturn(62.0);

        when(mealEntryService.calculateCarbs(mealEntry))
                .thenReturn(40.0);

        when(mealEntryService.calculateFat(mealEntry))
                .thenReturn(8.0);

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

        LocalDate date =
                LocalDate.of(2026, 10, 10);

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