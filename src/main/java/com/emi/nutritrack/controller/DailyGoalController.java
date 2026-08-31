package com.emi.nutritrack.controller;

import com.emi.nutritrack.entity.DailyGoal;
import com.emi.nutritrack.service.DailyGoalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dailyGoal")
public class DailyGoalController {

    private final DailyGoalService dailyGoalService;

    public DailyGoalController(DailyGoalService dailyGoalService)
    {
        this.dailyGoalService = dailyGoalService;
    }

    @GetMapping("/getAllDailyGoals")
    public List<DailyGoal> getAllDailyGoals()
    {
        return dailyGoalService.getAllDailyGoals();
    }

    @GetMapping("/getDailyGoalById/{id}")
    public Optional<DailyGoal> getDailyGoalById(@PathVariable Long id)
    {
        return dailyGoalService.getDailyGoalById(id);
    }

    @PostMapping("/addDailyGoal")
    public void addDailyGoal(@RequestBody DailyGoal dailyGoal)
    {
        dailyGoalService.addDailyGoal(dailyGoal);
    }

    @PutMapping("/updateDailyGoal/{id}")
    public void updateDailyGoal(@RequestBody DailyGoal dailyGoal, @PathVariable Long id)
    {
        dailyGoalService.updateDailyGoal(dailyGoal, id);
    }

    @DeleteMapping("/deleteDailyGoal/{id}")
    public void deleteDailyGoal(@PathVariable Long id)
    {
        dailyGoalService.deleteDailyGoal(id);
    }

}