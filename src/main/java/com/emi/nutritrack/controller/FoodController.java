/*Annotation	Elle récupère quoi ?	Exemple
@RequestBody	Les données dans le corps de la requête	un Food complet en JSON
@PathVariable	Une valeur directement dans l'URL	/foods/5 → récupère 5
@RequestParam	Un paramètre dans l'URL	/foods?name=Poulet
@RequestHeader	Une information dans les headers HTTP	token, langue, etc.*/



package com.emi.nutritrack.controller;

import com.emi.nutritrack.entity.Food;
import com.emi.nutritrack.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/foods")
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @PostMapping("/ajouter")
    public void addFood(@RequestBody Food food) {
        foodService.addFood(food);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimer(@PathVariable Long id) {
        foodService.deleteFood(id);
    }

    @GetMapping("/chercherById/{id}")
    public Optional<Food> findById(@PathVariable Long id)
    {
        return foodService.getFoodById(id);
    }

    @PutMapping("/updateFood/{id}")
    public void updateFood(@RequestBody Food food,@PathVariable Long id)
    {
        foodService.updateFood(food,id);
    }



}
