// package com.fittrack.api.repository;

// import com.fittrack.api.model.ExerciseEntry;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.time.LocalDate;
// import java.util.List;

// @Repository
// public interface ExerciseLogRepository extends JpaRepository<ExerciseEntry, Long> {
//     List<ExerciseEntry> findByUserIdAndDate(Long userId, LocalDate date);
//     List<ExerciseEntry> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
// }

// src/main/java/com/fittrack/repository/ExerciseLogRepository.java
package com.fittrack.api.repository;

import com.fittrack.api.model.ExerciseLog;
import com.fittrack.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Long> {
    List<ExerciseLog> findByUserAndWorkoutDate(User user, LocalDate date);
    List<ExerciseLog> findByUser(User user);
}