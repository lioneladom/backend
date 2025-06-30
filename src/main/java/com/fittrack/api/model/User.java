package com.fittrack.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Double height; // in cm
    private Double weight; // in kg
    private Date dateOfBirth;
    private String sex;
    private String country;
    private String activityLevel;
    private Double goalWeight;
    private String weeklyGoal;
    private Integer dailyCalorieGoal;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "macro_goals_id", referencedColumnName = "id")
    private MacroGoals macroGoals;

    @ElementCollection
    private Set<String> goals = new HashSet<>();
    
    @ElementCollection
    private Set<String> goalReasons = new HashSet<>();
    
    @ElementCollection
    private Set<String> healthBenefits = new HashSet<>();
    
    private String referralSource;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<FoodLog> foodLogs = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<ExerciseLog> exerciseLogs = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<WeightLog> weightLogs = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<MealEntry> mealEntries = new HashSet<>();

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum ActivityLevel {
        SEDENTARY, LIGHT, MODERATE, ACTIVE, VERY_ACTIVE
    }

    public enum Goal {
        LOSE, GAIN, TONE, MAINTAIN
    }

    // Getters
    public Integer getAge() {
        if (dateOfBirth == null) return null;
        return Period.between(
            dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
            LocalDate.now()
        ).getYears();
    }

    public String getGender() {
        return this.sex;
    }

    public String getGoal() {
        return this.weeklyGoal;
    }

    public String getActivityLevel() {
        return this.activityLevel;
    }

    public Integer getDailyCalorieGoal() {
        return this.dailyCalorieGoal;
    }

    public MacroGoals getMacroGoals() {
        return this.macroGoals;
    }

    // Setters
    public void setAge(Integer age) {
        if (age == null) {
            this.dateOfBirth = null;
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -age);
        this.dateOfBirth = cal.getTime();
    }

    public void setGender(String gender) {
        this.sex = gender;
    }

    public void setGender(Gender gender) {
        this.sex = gender != null ? gender.name() : null;
    }

    public void setGoal(String goal) {
        this.weeklyGoal = goal;
    }

    public void setGoal(Goal goal) {
        this.weeklyGoal = goal != null ? goal.name() : null;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel != null ? activityLevel.name() : null;
    }

    public void setDailyCalorieGoal(Integer dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    public void setMacroGoals(MacroGoals macroGoals) {
        this.macroGoals = macroGoals;
    }

    // Basic getters and setters (handled by @Data but explicitly defined for clarity)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(Double goalWeight) {
        this.goalWeight = goalWeight;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public void setReferralSource(String referralSource) {
        this.referralSource = referralSource;
    }

    public Set<String> getGoals() {
        return goals;
    }

    public void setGoals(Set<String> goals) {
        this.goals = goals;
    }

    public Set<String> getGoalReasons() {
        return goalReasons;
    }

    public void setGoalReasons(Set<String> goalReasons) {
        this.goalReasons = goalReasons;
    }

    public Set<String> getHealthBenefits() {
        return healthBenefits;
    }

    public void setHealthBenefits(Set<String> healthBenefits) {
        this.healthBenefits = healthBenefits;
    }
}