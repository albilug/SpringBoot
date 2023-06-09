package com.example.springboot.Exercise4.DAO;

import com.example.springboot.Meal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class MealDao {
    private List<Meal> meals = new ArrayList<>(Arrays.asList());
    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    public void deleteMeal(String mealname) {
        if(this.meals.removeIf(meal -> meal.getName().equals(mealname)));
    }

    public void updateMeal(Meal meal) {
        this.meals.removeIf(meal1 -> meal1.getName().equals(meal.getName()));
        this.meals.add(meal);
    }

    public List<Meal> getMeals() {
        return this.meals;
    }
}
