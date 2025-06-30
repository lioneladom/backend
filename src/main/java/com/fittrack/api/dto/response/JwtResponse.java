// src/main/java/com/fittrack/dto/response/JwtResponse.java
package com.fittrack.api.dto.response;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";

    public JwtResponse(String token) {
        this.token = token;
    }
}