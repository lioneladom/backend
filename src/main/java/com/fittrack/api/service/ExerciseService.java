// src/main/java/com/fittrack/service/ExerciseService.java
package com.fittrack.api.service;

import com.fittrack.api.dto.response.ExerciseResponse;
import com.fittrack.api.model.Exercise;
import com.fittrack.api.model.ExerciseLog;
import com.fittrack.api.model.User;
import com.fittrack.api.repository.ExerciseLogRepository;
import com.fittrack.api.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class ExerciseService {
    
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseLogRepository exerciseLogRepository;

    public List<ExerciseResponse> getAllExercises() {
        return exerciseRepository.findAll().stream()
                .map(this::convertToExerciseResponse)
                .collect(Collectors.toList());
    }

    public List<ExerciseResponse> searchExercises(String query) {
        return exerciseRepository.findByNameContainingIgnoreCase(query).stream()
                .map(this::convertToExerciseResponse)
                .collect(Collectors.toList());
    }

     public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }


    public ExerciseLog logExercise(User user, Exercise exercise, Integer sets, Integer reps, Double weight, Integer duration) {
        ExerciseLog exerciseLog = new ExerciseLog();
        exerciseLog.setUser(user);
        exerciseLog.setExercise(exercise);
        exerciseLog.setSets(sets);
        exerciseLog.setReps(reps);
        exerciseLog.setWeight(weight);
        exerciseLog.setDuration(duration);
        exerciseLog.setWorkoutDate(LocalDate.now());
        return exerciseLogRepository.save(exerciseLog);
    }

    public List<ExerciseLog> getExerciseLogs(User user, LocalDate date) {
        if (date != null) {
            return exerciseLogRepository.findByUserAndWorkoutDate(user, date);
        }
        return exerciseLogRepository.findByUser(user);
    }

    private ExerciseResponse convertToExerciseResponse(Exercise exercise) {
        ExerciseResponse response = new ExerciseResponse();
        response.setId(exercise.getId());
        response.setName(exercise.getName());
        response.setMuscleGroup(exercise.getMuscleGroup());
        response.setEquipment(exercise.getEquipment());
        response.setDifficulty(exercise.getDifficulty());
        return response;
    }


}