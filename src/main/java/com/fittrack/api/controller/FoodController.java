package com.fittrack.api.controller;

import com.fittrack.api.dto.request.FoodLogRequest;
import com.fittrack.api.dto.response.ApiResponse;
import com.fittrack.api.dto.response.FoodResponse;
import com.fittrack.api.model.Food;
import com.fittrack.api.model.FoodLog;
import com.fittrack.api.model.User;
import com.fittrack.api.service.FoodService;
import com.fittrack.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<FoodResponse>>> searchFood(@RequestParam String query) {
        List<FoodResponse> results = foodService.searchFood(query);
        return ResponseEntity.ok(ApiResponse.success("Food search completed successfully", results));
    }

    @GetMapping("/library")
    public ResponseEntity<ApiResponse<List<FoodResponse>>> getFoodLibrary() {
        List<FoodResponse> results = foodService.getAllFood();
        return ResponseEntity.ok(ApiResponse.success("Food library retrieved successfully", results));
    }

    @PostMapping("/log")
    public ResponseEntity<ApiResponse<FoodLog>> logFood(@RequestBody FoodLogRequest foodLogRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the Food entity using the foodId from the request
        Food food = foodService.getFoodById(foodLogRequest.getFoodId());
        if (food == null) {
            throw new RuntimeException("Food not found");
        }

        FoodLog foodLog = foodService.logFood(
            user,
            food,
            foodLogRequest.getQuantity(),
            foodLogRequest.getMealType()
        );
        return ResponseEntity.ok(ApiResponse.success("Food logged successfully", foodLog));
    }

    @GetMapping("/logs")
    public ResponseEntity<ApiResponse<List<FoodLog>>> getFoodLogs(
            @RequestParam(required = false) String date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate parsedDate = date != null ? LocalDate.parse(date) : null;
        List<FoodLog> logs = foodService.getFoodLogs(user, parsedDate);
        return ResponseEntity.ok(ApiResponse.success("Food logs retrieved successfully", logs));
    }
}
