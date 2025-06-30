package com.fittrack.api.dto.response;

import lombok.Data;

@Data
public class FoodResponse {
    private Long id;
    private String name;
    private Double calories;
    private Double protein;
    private Double carbs;
    private Double fat;
    private String servingSize;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }
}