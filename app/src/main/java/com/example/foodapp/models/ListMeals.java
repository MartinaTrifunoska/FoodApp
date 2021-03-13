package com.example.foodapp.models;

import java.util.List;

public class ListMeals {
    private List<Meal> meals;

    public ListMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }
}
