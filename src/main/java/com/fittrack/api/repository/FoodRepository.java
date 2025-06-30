// src/main/java/com/fittrack/repository/FoodRepository.java
package com.fittrack.api.repository;

import com.fittrack.api.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByNameContainingIgnoreCase(String name);
}