package com.example.springboot.Exercise4.Service;

import com.example.springboot.Exercise4.DAO.MealDao;
import com.example.springboot.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {
    private MealDao mealDao;

    @Autowired
    public MealService(MealDao mealDao) {
        this.mealDao = mealDao;
    }
    public void addMeal(Meal meal) {
        if (meal == null) throw new IllegalStateException("Meal cannot be null");
        if (meal.getName() == null || meal.getName().isEmpty()) throw new IllegalStateException("Meal name cannot be null or empty");
        if (meal.getDescription() == null || meal.getDescription().isEmpty()) throw new IllegalStateException("Meal description cannot be null or empty");
        if (meal.getPrice() <= 0) throw new IllegalStateException("Meal price cannot be negative or zero");
        if (meal.getPrice() > 100) throw new IllegalStateException("Meal price cannot be greater than 100");
        mealDao.addMeal(meal);
    }

    public void deleteMeal(String meal) {
        mealDao.deleteMeal(meal);
    }

    public void updateMeal(Meal meal) {
        mealDao.updateMeal(meal);
    }

    public List<Meal> getMeals() {
        return mealDao.getMeals();
    }
}
