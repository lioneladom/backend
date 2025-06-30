// src/main/java/com/fittrack/controller/ProgressController.java
package com.fittrack.api.controller;

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
    public ResponseEntity<List<ProgressResponse>> getWeightProgress(
            @RequestParam(required = false) String timeframe) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<ProgressResponse> progress = progressService.getWeightProgress(user, timeframe);
        return ResponseEntity.ok(progress);
    }

    @PostMapping("/weight")
    public ResponseEntity<?> logWeight(@RequestParam Double weight) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        progressService.logWeight(user, weight);
        return ResponseEntity.ok().build();
    }
}