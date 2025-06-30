// package com.fittrack.api.repository;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;

// import com.fittrack.api.model.WeightEntry;

// @Repository
// public interface WeightLogRepository extends JpaRepository<WeightEntry, Long> {
//     List<WeightEntry> findByUserIdAndDateBetweenOrderByDateAsc(Long userId, LocalDate startDate, LocalDate endDate);
    
//     @Query("SELECT w FROM WeightEntry w WHERE w.user.id = ?1 ORDER BY w.date DESC")
//     List<WeightEntry> findLatestByUserId(Long userId);
    
//     Optional<WeightEntry> findByUserIdAndDate(Long userId, LocalDate date);
// }

// src/main/java/com/fittrack/repository/WeightLogRepository.java
package com.fittrack.api.repository;

import com.fittrack.api.model.User;
import com.fittrack.api.model.WeightLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeightLogRepository extends JpaRepository<WeightLog, Long> {
    List<WeightLog> findByUserOrderByLogDateAsc(User user);
    List<WeightLog> findByUserAndLogDateBetween(User user, LocalDate startDate, LocalDate endDate);
}