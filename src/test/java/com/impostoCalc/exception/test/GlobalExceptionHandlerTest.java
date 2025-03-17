package com.impostoCalc.exception.test;

import com.impostoCalc.exception.ErrorResponse;
import com.impostoCalc.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void testHandleIllegalArgumentException() {
        // Arrange
        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");
        WebRequest webRequest = mock(WebRequest.class);
        when(webRequest.getDescription(false)).thenReturn("/test");

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleIllegalArgumentException(exception, webRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Invalid argument", response.getBody().getMessage());
        assertEquals("/test", response.getBody().getPath());
    }

    @Test
    void testHandleRuntimeException() {
        // Arrange
        RuntimeException exception = new RuntimeException("Runtime error");
        WebRequest webRequest = mock(WebRequest.class);
        when(webRequest.getDescription(false)).thenReturn("/test");

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleRuntimeException(exception, webRequest);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Runtime error", response.getBody().getMessage());
        assertEquals("/test", response.getBody().getPath());
    }

    @Test
    void testHandleAllExceptions() {
        // Arrange
        Exception exception = new Exception("Generic error");
        WebRequest webRequest = mock(WebRequest.class);
        when(webRequest.getDescription(false)).thenReturn("/test");

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleAllExceptions(exception, webRequest);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Erro interno no servidor", response.getBody().getMessage());
        assertEquals("/test", response.getBody().getPath());
    }
}