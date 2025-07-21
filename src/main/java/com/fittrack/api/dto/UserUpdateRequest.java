package com.fittrack.api.dto;

import com.fittrack.api.model.MacroGoals;

import jakarta.validation.constraints.*;

public class UserUpdateRequest {
    @Size(max = 50)
    private String name;

    @Min(0)
    @Max(150)
    private Integer age;

    @Pattern(regexp = "MALE|FEMALE|OTHER", message = "Gender must be MALE, FEMALE, or OTHER")
    private String gender;

    @DecimalMin("0.0")
    private Double height;

    @DecimalMin("0.0")
    private Double weight;

    @DecimalMin("0.0")
    private Double goalWeight;

    @Pattern(regexp = "SEDENTARY|LIGHT|MODERATE|ACTIVE|VERY_ACTIVE", message = "Invalid activity level")
    private String activityLevel;

    @Pattern(regexp = "LOSE|GAIN|TONE|MAINTAIN", message = "Invalid goal")
    private String goal;

    @Min(0)
    private Integer dailyCalorieGoal;

    private MacroGoals macroGoals;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
    public Double getGoalWeight() { return goalWeight; }
    public void setGoalWeight(Double goalWeight) { this.goalWeight = goalWeight; }
    public String getActivityLevel() { return activityLevel; }
    public void setActivityLevel(String activityLevel) { this.activityLevel = activityLevel; }
    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }
    public Integer getDailyCalorieGoal() { return dailyCalorieGoal; }
    public void setDailyCalorieGoal(Integer dailyCalorieGoal) { this.dailyCalorieGoal = dailyCalorieGoal; }
    public MacroGoals getMacroGoals() { return macroGoals; }
    public void setMacroGoals(MacroGoals macroGoals) { this.macroGoals = macroGoals; }
}
