package com.emi.nutritrack.service;

import com.emi.nutritrack.entity.MealEntry;
import com.emi.nutritrack.repository.MealEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class MealEntryService {

    private final MealEntryRepository mealEntryRepository;

    public MealEntryService(MealEntryRepository mealEntryRepository)
    {
        this.mealEntryRepository = mealEntryRepository;
    }

    public void addMealEntry(MealEntry mealEntry)
    {
        mealEntryRepository.save(mealEntry);
    }

    public List<MealEntry> getAllMealEntries()
    {
        return mealEntryRepository.findAll();
    }

    public Optional<MealEntry> getMealEntryById(Long id)
    {
        return mealEntryRepository.findById(id);
    }

    public void deleteMealEntry(Long id)
    {
        mealEntryRepository.deleteById(id);
    }

    public void updateMealEntry(MealEntry mealEntry, Long id)
    {
        Optional<MealEntry> existe = mealEntryRepository.findById(id);

        if(existe.isPresent())
        {
            MealEntry mealExistant = existe.get();

            mealExistant.setQuantity(mealEntry.getQuantity());
            mealExistant.setMealType(mealEntry.getMealType());
            mealExistant.setConsumedAt(mealEntry.getConsumedAt());
            mealExistant.setUser(mealEntry.getUser());
            mealExistant.setFood(mealEntry.getFood());

            mealEntryRepository.save(mealExistant);
        }
    }

    public List<MealEntry> getMealEntriesByDate(Long userId, LocalDate date)
    {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        return mealEntryRepository.findByUserIdAndConsumedAtBetween(
                userId,
                start,
                end
        );
    }

    public double calculateCalories(MealEntry mealEntry)
    {
        double calories = mealEntry.getFood().getCalories();
        double quantityReference = mealEntry.getFood().getQuantityReference();
        double quantity = mealEntry.getQuantity();

        return calories * (quantity / quantityReference);
    }

    public double calculateProtein(MealEntry mealEntry)
    {
        double protein = mealEntry.getFood().getProtein();
        double quantityReference = mealEntry.getFood().getQuantityReference();
        double quantity = mealEntry.getQuantity();

        return protein * (quantity / quantityReference);
    }

    public double calculateCarbs(MealEntry mealEntry)
    {
        double carbs = mealEntry.getFood().getCarbs();
        double quantityReference = mealEntry.getFood().getQuantityReference();
        double quantity = mealEntry.getQuantity();

        return carbs * (quantity / quantityReference);
    }

    public double calculateFat(MealEntry mealEntry)
    {
        double fat = mealEntry.getFood().getFat();
        double quantityReference = mealEntry.getFood().getQuantityReference();
        double quantity = mealEntry.getQuantity();

        return fat * (quantity / quantityReference);
    }
}