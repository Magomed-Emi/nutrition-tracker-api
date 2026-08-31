package com.emi.nutritrack.controller;

import com.emi.nutritrack.entity.MealEntry;
import com.emi.nutritrack.service.MealEntryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/mealEntry")
public class MealEntryController {

    private final MealEntryService mealEntryService;

    public MealEntryController(MealEntryService mealEntryService) {
        this.mealEntryService = mealEntryService;
    }

    @GetMapping("/getMealEntry")
    public List<MealEntry> getMealEntry() {
        return mealEntryService.getAllMealEntries();
    }

    @GetMapping("/getMealEntryById/{id}")
    public Optional<MealEntry> getMealEntryById(@PathVariable Long id) {
        return mealEntryService.getMealEntryById(id);
    }

    @PostMapping("/addMealEntry")
    public void addMealEntry(@RequestBody MealEntry mealEntry) {
        mealEntryService.addMealEntry(mealEntry);
    }

    @DeleteMapping("/deleteMealEntry/{id}")
    public void deleteMealEntry(@PathVariable Long id) {
        mealEntryService.deleteMealEntry(id);
    }

    @PutMapping("updateMealEntry/{id}")
    private void updateMealEntry(@RequestBody MealEntry mealEntry, @PathVariable Long id) {
        mealEntryService.updateMealEntry(mealEntry, id);
    }
}