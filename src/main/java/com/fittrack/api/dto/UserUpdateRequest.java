package com.fittrack.api.dto;

import com.fittrack.api.model.MacroGoals;

public class UserUpdateRequest {
    private String name;
    private Integer age;
    private String gender;
    private Double height;
    private Double weight;
    private Double goalWeight;
    private String activityLevel;
    private String goal;
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