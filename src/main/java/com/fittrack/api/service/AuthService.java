package com.fittrack.api.service;


import com.fittrack.api.dto.request.LoginRequest;
import com.fittrack.api.dto.request.SignupRequest;
import com.fittrack.api.dto.response.JwtResponse;
import com.fittrack.api.dto.response.ApiResponse;
import com.fittrack.api.model.User;
import com.fittrack.api.dto.response.MessageResponse;
import com.fittrack.api.exception.EmailAlreadyExistsException;
import com.fittrack.api.repository.UserRepository;
import com.fittrack.api.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            return new JwtResponse(jwt);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email or password");
        }
    }

    public MessageResponse registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setHeight(signUpRequest.getHeight());
        user.setWeight(signUpRequest.getWeight());
        user.setDateOfBirth(signUpRequest.getDateOfBirth());
        user.setGender(signUpRequest.getSex());
        user.setCountry(signUpRequest.getCountry());
        user.setActivityLevel(signUpRequest.getActivityLevel());
        user.setGoalWeight(signUpRequest.getGoalWeight());
        user.setGoals(signUpRequest.getGoals());
        user.setGoalReasons(signUpRequest.getGoalReasons());
        user.setHealthBenefits(signUpRequest.getHealthBenefits());
        user.setReferralSource(signUpRequest.getReferralSource());

        userRepository.save(user);

        return new MessageResponse(true, "User registered successfully!");
    }
}
