package com.fittrack.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "macro_goals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MacroGoals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double protein; // in grams (changed from Integer to Double)
    private Double carbs;   // in grams (changed from Integer to Double)
    private Double fat;     // in grams (changed from Integer to Double)
    private Integer calories; // in kcal (kept as Integer)

    @OneToOne(mappedBy = "macroGoals")
    private User user;

    // Getters and setters are now properly typed
    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    // Added for calories
    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
}