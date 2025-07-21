package com.fittrack.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Double height;
    private Double weight;
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
    @JsonIgnore
    private Set<FoodLog> foodLogs = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ExerciseLog> exerciseLogs = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<WeightLog> weightLogs = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
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

    // Custom getter for age since it is calculated
    public Integer getAge() {
        if (dateOfBirth == null) return null;
        return Period.between(
            dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
            LocalDate.now()
        ).getYears();
    }

    // Custom setter for age to set dateOfBirth accordingly
    public void setAge(Integer age) {
        if (age == null) {
            this.dateOfBirth = null;
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -age);
        this.dateOfBirth = cal.getTime();
    }

    // Custom getter and setter for gender mapping to sex field
    public String getGender() {
        return this.sex;
    }

    public void setGender(String gender) {
        this.sex = gender;
    }

    // Overloaded setter for gender enum
    public void setGender(Gender gender) {
        this.sex = gender != null ? gender.name() : null;
    }

    // Custom getter and setter for goal mapping to weeklyGoal field
    public String getGoal() {
        return this.weeklyGoal;
    }

    public void setGoal(String goal) {
        this.weeklyGoal = goal;
    }

    // Overloaded setter for goal enum
    public void setGoal(Goal goal) {
        this.weeklyGoal = goal != null ? goal.name() : null;
    }

    // Custom getter and setter for activityLevel field with enum overload
    public String getActivityLevel() {
        return this.activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel != null ? activityLevel.name() : null;
    }
}
