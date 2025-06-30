// src/main/java/com/fittrack/dto/request/FoodLogRequest.java
package com.fittrack.api.dto.request;

import com.fittrack.api.model.Food;
import com.fittrack.api.repository.FoodRepository;

import lombok.Data;

@Data
public class FoodLogRequest {
    private Long foodId;
    private Double quantity;
    private String mealType;


}