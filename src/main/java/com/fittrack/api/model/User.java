package com.fittrack.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"foodLogs", "exerciseLogs", "weightLogs", "mealEntries"})
@EqualsAndHashCode(exclude = {"foodLogs", "exerciseLogs", "weightLogs", "mealEntries"})
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
    @Builder.Default
    private Set<String> goals = new HashSet<>();

    @ElementCollection
    @Builder.Default
    private Set<String> goalReasons = new HashSet<>();

    @ElementCollection
    @Builder.Default
    private Set<String> healthBenefits = new HashSet<>();

    private String referralSource;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private Set<FoodLog> foodLogs = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private Set<ExerciseLog> exerciseLogs = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private Set<WeightLog> weightLogs = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
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

    public Integer getAge() {
        if (dateOfBirth == null) return null;
        return Period.between(
            dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
            LocalDate.now()
        ).getYears();
    }

    public void setAge(Integer age) {
        if (age == null) {
            this.dateOfBirth = null;
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -age);
        this.dateOfBirth = cal.getTime();
    }

    public String getGender() {
        return this.sex;
    }

    public void setGender(String gender) {
        this.sex = gender;
    }

    public void setGender(Gender gender) {
        this.sex = gender != null ? gender.name() : null;
    }

    public String getGoal() {
        return this.weeklyGoal;
    }

    public void setGoal(String goal) {
        this.weeklyGoal = goal;
    }

    public void setGoal(Goal goal) {
        this.weeklyGoal = goal != null ? goal.name() : null;
    }

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
