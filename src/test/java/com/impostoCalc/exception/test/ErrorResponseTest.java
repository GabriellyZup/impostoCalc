package com.impostoCalc.exception.test;

import com.impostoCalc.exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testErrorResponse() {
        // Arrange
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Invalid request";
        String path = "/api/test";

        // Act
        ErrorResponse errorResponse = new ErrorResponse(status, message, path);

        // Assert
        assertEquals(status.value(), errorResponse.getStatus());
        assertEquals(status.getReasonPhrase(), errorResponse.getError());
        assertEquals(message, errorResponse.getMessage());
        assertEquals(path, errorResponse.getPath());
        assertNotNull(errorResponse.getTimestamp());
    }

    @Test
    void testSetValidationErrors() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Validation error", "/api/test");
        Map<String, String> validationErrors = Map.of("field1", "must not be null");

        // Act
        errorResponse.setValidationErrors(validationErrors);

        // Assert
        assertEquals(validationErrors, errorResponse.getValidationErrors());
    }
}