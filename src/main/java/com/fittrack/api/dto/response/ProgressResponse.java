// src/main/java/com/fittrack/dto/response/ProgressResponse.java
package com.fittrack.api.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProgressResponse {
    private LocalDate date;
    private Double weight;

    public ProgressResponse(LocalDate date, Double weight) {
        this.date = date;
        this.weight = weight;
    }
}