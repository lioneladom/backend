// src/main/java/com/fittrack/exception/ResourceNotFoundException.java
package com.fittrack.api.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}