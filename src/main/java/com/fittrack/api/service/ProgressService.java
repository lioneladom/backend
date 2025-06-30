// src/main/java/com/fittrack/service/ProgressService.java
package com.fittrack.api.service;

import com.fittrack.api.dto.response.ProgressResponse;
import com.fittrack.api.model.User;
import com.fittrack.api.model.WeightLog;
import com.fittrack.api.repository.WeightLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgressService {
    
    @Autowired
    private WeightLogRepository weightLogRepository;

    public List<ProgressResponse> getWeightProgress(User user, String timeframe) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30); // Default to 30 days
        
        if ("week".equalsIgnoreCase(timeframe)) {
            startDate = endDate.minusDays(7);
        } else if ("month".equalsIgnoreCase(timeframe)) {
            startDate = endDate.minusMonths(1);
        } else if ("year".equalsIgnoreCase(timeframe)) {
            startDate = endDate.minusYears(1);
        }

        List<WeightLog> logs = weightLogRepository.findByUserAndLogDateBetween(user, startDate, endDate);
        
        return logs.stream()
                .map(log -> new ProgressResponse(log.getLogDate(), log.getWeight()))
                .collect(Collectors.toList());
    }

    public WeightLog logWeight(User user, Double weight) {
        WeightLog weightLog = new WeightLog();
        weightLog.setUser(user);
        weightLog.setWeight(weight);
        weightLog.setLogDate(LocalDate.now());
        return weightLogRepository.save(weightLog);
    }
}