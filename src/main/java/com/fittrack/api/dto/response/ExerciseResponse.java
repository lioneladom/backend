// src/main/java/com/fittrack/dto/response/ExerciseResponse.java
package com.fittrack.api.dto.response;

import lombok.Data;

@Data
public class ExerciseResponse {
    private Long id;
    private String name;
    private String muscleGroup;
    private String equipment;
    private String difficulty;
}