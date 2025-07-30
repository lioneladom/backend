// src/main/java/com/fittrack/service/FoodService.java
package com.fittrack.api.service;

import com.fittrack.api.dto.response.FoodResponse;
import com.fittrack.api.model.Food;
import com.fittrack.api.model.FoodLog;
import com.fittrack.api.model.User;
import com.fittrack.api.repository.FoodLogRepository;
import com.fittrack.api.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {
    
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodLogRepository foodLogRepository;

    public List<FoodResponse> searchFood(String query) {
        List<Food> foods = foodRepository.findByNameContainingIgnoreCase(query);
        return foods.stream()
                .map(this::convertToFoodResponse)
                .collect(Collectors.toList());
    }

    public FoodLog logFood(User user, Food food, Double quantity, String mealType) {
        FoodLog foodLog = new FoodLog();
        foodLog.setUser(user);
        foodLog.setFood(food);
        foodLog.setQuantity(quantity);
        foodLog.setMealType(mealType);
        foodLog.setLogDate(LocalDate.now());
        return foodLogRepository.save(foodLog);
    }

    public List<FoodLog> getFoodLogs(User user, LocalDate date) {
        if (date != null) {
            return foodLogRepository.findByUserAndLogDate(user, date);
        }
        return foodLogRepository.findByUser(user);
    }

    public Food getFoodById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));
    }

    private FoodResponse convertToFoodResponse(Food food) {
        FoodResponse response = new FoodResponse();
        response.setId(food.getId());
        response.setName(food.getName());
        response.setCalories(food.getCalories());
        response.setProtein(food.getProtein());
        response.setCarbs(food.getCarbs());
        response.setFat(food.getFat());
        response.setServingSize(food.getServingSize());
        return response;
    }

    public List<FoodResponse> getAllFood() {
        List<Food> foods = foodRepository.findAll();
        return foods.stream()
                .map(this::convertToFoodResponse)
                .collect(Collectors.toList());
    }
}