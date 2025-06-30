// // src/main/java/com/fittrack/repository/FoodRepository.java
// package com.fittrack.api.repository;

// import com.fittrack.api.model.Food;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public interface FoodLogRepository extends JpaRepository<Food, Long> {
//     List<Food> findByNameContainingIgnoreCase(String name);
// }

// src/main/java/com/fittrack/repository/FoodLogRepository.java
package com.fittrack.api.repository;

import com.fittrack.api.model.FoodLog;
import com.fittrack.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FoodLogRepository extends JpaRepository<FoodLog, Long> {
    List<FoodLog> findByUserAndLogDate(User user, LocalDate date);
    List<FoodLog> findByUser(User user);
}