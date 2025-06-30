package com.fittrack.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

@Data
public class SignupRequest {
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private Double height;
    private Double weight;
    private Date dateOfBirth;
    private String sex;
    private String country;
    private String activityLevel;
    private Double goalWeight;
    private String weeklyGoal;
    private Set<String> goals;
    private Set<String> goalReasons;
    private Set<String> healthBenefits;
    private String referralSource;

    // Age calculation (modern java.time version)
    public Integer getAge() {
        if (dateOfBirth == null) return null;
        return Period.between(
            dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
            LocalDate.now()
        ).getYears();
    }

    // Gender getter (maps to sex field)
    public String getGender() {
        return this.sex;
    }

    // Goal getter
    public String getGoal() {
        return this.weeklyGoal;
    }

    // Explicit getters and setters (complementing @Data)
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public Double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(Double goalWeight) {
        this.goalWeight = goalWeight;
    }

    public String getWeeklyGoal() {
        return weeklyGoal;
    }

    public void setWeeklyGoal(String weeklyGoal) {
        this.weeklyGoal = weeklyGoal;
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

    public String getReferralSource() {
        return referralSource;
    }

    public void setReferralSource(String referralSource) {
        this.referralSource = referralSource;
    }
}