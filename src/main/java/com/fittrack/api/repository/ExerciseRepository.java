// package com.fittrack.api.repository;

// import com.fittrack.api.model.Exercise;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
//     List<Exercise> findByNameContainingIgnoreCase(String name);
//     List<Exercise> findByUserIdOrUserIsNull(Long userId);
//     List<Exercise> findByCategory(String category);
// }

// src/main/java/com/fittrack/repository/ExerciseRepository.java
package com.fittrack.api.repository;

import com.fittrack.api.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByNameContainingIgnoreCase(String name);
    List<Exercise> findByMuscleGroup(String muscleGroup);
}