package com.fittrack.api.dto;

import com.fittrack.api.model.MacroGoals;

public class UserProfileDto {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String gender;
    private Double height;
    private Double weight;
    private Double goalWeight;
    private String activityLevel;
    private String goal;
    private Integer dailyCalorieGoal;
    private MacroGoals macroGoals;

    // Constructor
    public UserProfileDto(Long id, String name, String email, Integer age, String gender, 
                         Double height, Double weight, Double goalWeight, String activityLevel,
                         String goal, Integer dailyCalorieGoal, MacroGoals macroGoals) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.goalWeight = goalWeight;
        this.activityLevel = activityLevel;
        this.goal = goal;
        this.dailyCalorieGoal = dailyCalorieGoal;
        this.macroGoals = macroGoals;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Integer getAge() { return age; }
    public String getGender() { return gender; }
    public Double getHeight() { return height; }
    public Double getWeight() { return weight; }
    public Double getGoalWeight() { return goalWeight; }
    public String getActivityLevel() { return activityLevel; }
    public String getGoal() { return goal; }
    public Integer getDailyCalorieGoal() { return dailyCalorieGoal; }
    public MacroGoals getMacroGoals() { return macroGoals; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setGoalWeight(Double goalWeight) {
        this.goalWeight = goalWeight;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setDailyCalorieGoal(Integer dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    public void setMacroGoals(MacroGoals macroGoals) {
        this.macroGoals = macroGoals;
    }
}