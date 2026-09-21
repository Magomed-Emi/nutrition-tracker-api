package com.emi.nutritrack.service;

import com.emi.nutritrack.dto.MealEntryRequest;
import com.emi.nutritrack.dto.MealEntryResponse;
import com.emi.nutritrack.entity.Food;
import com.emi.nutritrack.entity.MealEntry;
import com.emi.nutritrack.entity.User;
import com.emi.nutritrack.exception.ResourceNotFoundException;
import com.emi.nutritrack.repository.FoodRepository;
import com.emi.nutritrack.repository.MealEntryRepository;
import com.emi.nutritrack.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class MealEntryService {

    private final MealEntryRepository mealEntryRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    public MealEntryService(
            MealEntryRepository mealEntryRepository,
            FoodRepository foodRepository,
            UserRepository userRepository)
    {
        this.mealEntryRepository = mealEntryRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
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

    public MealEntryResponse createMealEntry(MealEntryRequest request)
    {
        Food food = foodRepository.findById(request.getFoodId())
                .orElseThrow(() -> new ResourceNotFoundException("Food introuvable"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User introuvable"));

        MealEntry mealEntry = new MealEntry();

        mealEntry.setFood(food);
        mealEntry.setUser(user);
        mealEntry.setQuantity(request.getQuantity());
        mealEntry.setMealType(request.getMealType());
        mealEntry.setConsumedAt(LocalDateTime.now());

        MealEntry savedMealEntry = mealEntryRepository.save(mealEntry);

        MealEntryResponse response = new MealEntryResponse();

        response.setId(savedMealEntry.getId());
        response.setQuantity(savedMealEntry.getQuantity());
        response.setMealType(savedMealEntry.getMealType());
        response.setConsumedAt(savedMealEntry.getConsumedAt());
        response.setUserId(savedMealEntry.getUser().getId());
        response.setFoodId(savedMealEntry.getFood().getId());
        response.setFoodName(savedMealEntry.getFood().getName());

        return response;
    }
}