package com.impostoCalc.exception.test;

import com.impostoCalc.exception.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testErrorResponseFields() {
        // Arrange
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Validation error";
        String path = "/test";

        // Act
        ErrorResponse errorResponse = new ErrorResponse(status, message, path);

        // Assert
        assertNotNull(errorResponse.getTimestamp());
        assertEquals(status.value(), errorResponse.getStatus());
        assertEquals(status.getReasonPhrase(), errorResponse.getError());
        assertEquals(message, errorResponse.getMessage());
        assertEquals(path, errorResponse.getPath());
    }

    @Test
    void testValidationErrors() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Validation error", "/test");
        Map<String, String> validationErrors = Map.of("field1", "must not be null", "field2", "must be a valid email");

        // Act
        errorResponse.setValidationErrors(validationErrors);

        // Assert
        assertNotNull(errorResponse.getValidationErrors());
        assertEquals(2, errorResponse.getValidationErrors().size());
        assertEquals("must not be null", errorResponse.getValidationErrors().get("field1"));
        assertEquals("must be a valid email", errorResponse.getValidationErrors().get("field2"));
    }
}