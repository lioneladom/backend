package com.fittrack.api.controller;

import com.fittrack.api.dto.request.ExerciseLogRequest;
import com.fittrack.api.dto.response.ExerciseResponse;
import com.fittrack.api.model.Exercise;
import com.fittrack.api.model.ExerciseLog;
import com.fittrack.api.model.User;
import com.fittrack.api.service.ExerciseService;
import com.fittrack.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private UserService userService;

    @GetMapping("/library")
    public ResponseEntity<List<ExerciseResponse>> getExerciseLibrary() {
        List<ExerciseResponse> exercises = exerciseService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }

    @PostMapping("/log")
    public ResponseEntity<?> logExercise(@RequestBody ExerciseLogRequest exerciseLogRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Corrected: Using the proper getters from ExerciseLogRequest
        Long exerciseId = exerciseLogRequest.getExerciseId();
        Integer reps = exerciseLogRequest.getReps();
        Integer sets = exerciseLogRequest.getSets();
        Double weight = exerciseLogRequest.getWeight();
        Integer duration = exerciseLogRequest.getDuration();

        // You'll need to fetch the Exercise entity using the exerciseId
        Exercise exercise = exerciseService.getExerciseById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        ExerciseLog exerciseLog = exerciseService.logExercise(user, exercise, reps, sets, weight, duration);
        return ResponseEntity.ok(exerciseLog);
    }

     @GetMapping("/logs")
    public ResponseEntity<List<ExerciseLog>> getExerciseLogs(
            @RequestParam(required = false) String date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Parse the date string to LocalDate if provided
        LocalDate parsedDate = date != null ? LocalDate.parse(date) : null;
        List<ExerciseLog> logs = exerciseService.getExerciseLogs(user, parsedDate);
        return ResponseEntity.ok(logs);
    }
}