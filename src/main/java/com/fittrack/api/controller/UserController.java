package com.fittrack.api.controller;

import com.fittrack.api.dto.response.ApiResponse;
import com.fittrack.api.exception.UserNotFoundException;
import com.fittrack.api.model.User;
import com.fittrack.api.dto.UserProfileDto;
import com.fittrack.api.dto.UserUpdateRequest;
import com.fittrack.api.repository.UserRepository;
import com.fittrack.api.security.CurrentUser;
import com.fittrack.api.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserProfileDto> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        UserProfileDto userProfile = new UserProfileDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getGender(),
                user.getHeight(),
                user.getWeight(),
                user.getGoalWeight(),
                user.getActivityLevel(),
                user.getGoal(),
                user.getDailyCalorieGoal(),
                user.getMacroGoals()
        );
        
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping("/me")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUser(@CurrentUser UserPrincipal currentUser,
                                        @RequestBody UserUpdateRequest updateRequest) {
        User user = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        if (updateRequest.getName() != null) {
            user.setName(updateRequest.getName());
        }
        
        if (updateRequest.getAge() != null) {
            user.setAge(updateRequest.getAge());
        }
        
        if (updateRequest.getGender() != null) {
            user.setGender(updateRequest.getGender());
        }
        
        if (updateRequest.getHeight() != null) {
            user.setHeight(updateRequest.getHeight());
        }
        
        if (updateRequest.getWeight() != null) {
            user.setWeight(updateRequest.getWeight());
        }
        
        if (updateRequest.getGoalWeight() != null) {
            user.setGoalWeight(updateRequest.getGoalWeight());
        }
        
        if (updateRequest.getActivityLevel() != null) {
            user.setActivityLevel(updateRequest.getActivityLevel());
        }
        
        if (updateRequest.getGoal() != null) {
            user.setGoal(updateRequest.getGoal());
        }
        
        if (updateRequest.getDailyCalorieGoal() != null) {
            user.setDailyCalorieGoal(updateRequest.getDailyCalorieGoal());
        }
        
        if (updateRequest.getMacroGoals() != null) {
            if (user.getMacroGoals() == null) {
                user.setMacroGoals(updateRequest.getMacroGoals());
            } else {
                user.getMacroGoals().setProtein(updateRequest.getMacroGoals().getProtein());
                user.getMacroGoals().setCarbs(updateRequest.getMacroGoals().getCarbs());
                user.getMacroGoals().setFat(updateRequest.getMacroGoals().getFat());
            }
        }
        
        userRepository.save(user);
        
        return ResponseEntity.ok(new ApiResponse(true, "User updated successfully"));
    }
}
