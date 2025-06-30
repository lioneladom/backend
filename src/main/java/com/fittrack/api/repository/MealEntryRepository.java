package com.fittrack.api.repository;

import com.fittrack.api.model.MealEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealEntryRepository extends JpaRepository<MealEntry, Long> {
    List<MealEntry> findByUserIdAndDate(Long userId, LocalDate date);
    List<MealEntry> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
    List<MealEntry> findByUserIdAndDateAndMealType(Long userId, LocalDate date, MealEntry.MealType mealType);
}