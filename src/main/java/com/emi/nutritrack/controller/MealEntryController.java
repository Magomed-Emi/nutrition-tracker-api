package com.emi.nutritrack.controller;

import com.emi.nutritrack.dto.MealEntryRequest;
import com.emi.nutritrack.entity.MealEntry;
import com.emi.nutritrack.service.MealEntryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.emi.nutritrack.dto.MealEntryResponse;

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

    @PostMapping("/create")
    public MealEntryResponse createMealEntry(@Valid @RequestBody MealEntryRequest request)
    {
        return mealEntryService.createMealEntry(request);
    }

    @DeleteMapping("/deleteMealEntry/{id}")
    public void deleteMealEntry(@PathVariable Long id) {
        mealEntryService.deleteMealEntry(id);
    }

    @PutMapping("/updateMealEntry/{id}")
    public void updateMealEntry(
            @RequestBody MealEntry mealEntry,
            @PathVariable Long id) {
        mealEntryService.updateMealEntry(mealEntry, id);
    }
}