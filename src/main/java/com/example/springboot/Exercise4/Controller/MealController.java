package com.example.springboot.Exercise4.Controller;

import com.example.springboot.Exercise4.RestaurantConfig;
import com.example.springboot.Exercise4.Service.MealService;
import com.example.springboot.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MealController {
    private MealService mealService;
    private RestaurantConfig restaurantConfig;

    @Autowired
    public MealController(MealService mealService, RestaurantConfig restaurantConfig) {
        this.mealService = mealService;
        this.restaurantConfig = restaurantConfig;
    }

    @GetMapping(value = "/get/meals")
    public ResponseEntity<List<Meal>> getMeals() {
        return ResponseEntity.ok(mealService.getMeals());
    }

    @PutMapping(value = "/put/meal")
    public ResponseEntity<String> putMeal(@RequestBody Meal meal) {
        try {
            mealService.addMeal(meal);
            return ResponseEntity.ok("Meal Added!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/meal/{mealName}")
    public ResponseEntity<String> deleteMeal(@PathVariable String mealName) {
        mealService.deleteMeal(mealName);
        return ResponseEntity.ok("Meal Deleted");
    }

    @GetMapping("/restaurant-config")
    public ResponseEntity<RestaurantConfig> getRestaurantConfig() {
        this.restaurantConfig.setTodaysDiscount(10.0);
        this.restaurantConfig.setMaxPrice(202.00);
        this.restaurantConfig.setMinPrice(1.04);
        return ResponseEntity.ok(restaurantConfig);
    }
}

