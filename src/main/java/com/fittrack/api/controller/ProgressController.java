package com.fittrack.api.controller;

import com.fittrack.api.dto.response.ApiResponse;
import com.fittrack.api.dto.response.ProgressResponse;
import com.fittrack.api.model.User;
import com.fittrack.api.service.ProgressService;
import com.fittrack.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @Autowired
    private UserService userService;

    @GetMapping("/weight")
    public ResponseEntity<ApiResponse<List<ProgressResponse>>> getWeightProgress(
            @RequestParam(required = false) String timeframe) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<ProgressResponse> progress = progressService.getWeightProgress(user, timeframe);
        return ResponseEntity.ok(ApiResponse.success("Weight progress retrieved successfully", progress));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProgressResponse>>> getProgress() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // You can customize this to return general progress data
        List<ProgressResponse> progress = progressService.getGeneralProgress(user);
        return ResponseEntity.ok(ApiResponse.success("General progress retrieved successfully", progress));
    }

    @GetMapping("/weight/logs")
    public ResponseEntity<ApiResponse<List<ProgressResponse>>> getWeightLogs() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<ProgressResponse> weightLogs = progressService.getWeightLogs(user);
        return ResponseEntity.ok(ApiResponse.success("Weight logs retrieved successfully", weightLogs));
    }

    @PostMapping("/weight")
    public ResponseEntity<ApiResponse<Void>> logWeight(@RequestBody WeightRequest weightRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        progressService.logWeight(user, weightRequest.getWeight());
        return ResponseEntity.ok(ApiResponse.success("Weight logged successfully"));
    }

    public static class WeightRequest {
        private Double weight;

        public Double getWeight() {
            return weight;
        }

        public void setWeight(Double weight) {
            this.weight = weight;
        }
    }
}
