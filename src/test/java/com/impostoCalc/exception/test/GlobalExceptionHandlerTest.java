package com.impostoCalc.exception.test;

import com.impostoCalc.exception.ErrorResponse;
import com.impostoCalc.exception.GlobalExceptionHandler;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class GlobalExceptionHandlerTest {
//
//    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();
//
//    @Test
//    void handleIllegalArgumentException_DeveRetornarBadRequestComMensagem() {
//        // Arrange
//        IllegalArgumentException ex = new IllegalArgumentException("Parâmetro inválido");
//        WebRequest request = mock(WebRequest.class);
//        when(request.getDescription(false)).thenReturn("/api/tipos");
//
//        // Act
//        ResponseEntity<ErrorResponse> response =
//                exceptionHandler.handleIllegalArgumentException(ex, request);
//
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        ErrorResponse body = response.getBody();
//        assertNotNull(body);
//        assertEquals("Parâmetro inválido", body.getMessage());
//        assertEquals("/api/tipos", body.getPath());
//        assertEquals(HttpStatus.BAD_REQUEST.value(), body.getStatus());
//    }
//
//    @Test
//    void handleMethodArgumentNotValid_DeveRetornarErrosValidacaoDetalhados() {
//        // Arrange
//        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
//        FieldError fieldError = new FieldError(
//                "TaxRequest",
//                "aliquota",
//                "deve estar entre 0 e 100"
//        );
//        when(ex.getBindingResult().getFieldErrors()).thenReturn(List.of(fieldError));
//
//        HttpHeaders headers = new HttpHeaders();
//        WebRequest request = mock(WebRequest.class);
//        when(request.getDescription(false)).thenReturn("/api/tipos");
//
//        // Act
//        ResponseEntity<Object> response = exceptionHandler.handleMethodArgumentNotValid(
//                ex, headers, HttpStatus.BAD_REQUEST, request
//        );
//
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        ErrorResponse body = (ErrorResponse) response.getBody();
//        assertNotNull(body);
//
//        Map<String, String> errors = body.getValidationErrors();
//        assertNotNull(errors);
//        assertEquals(1, errors.size());
//        assertEquals("deve estar entre 0 e 100", errors.get("aliquota"));
//        assertEquals("Erro de validação", body.getMessage());
//    }
//
//    @Test
//    void handleRuntimeException_DeveRetornarInternalServerError() {
//        // Arrange
//        RuntimeException ex = new RuntimeException("Erro inesperado");
//        WebRequest request = mock(WebRequest.class);
//        when(request.getDescription(false)).thenReturn("/api/calculo");
//
//        // Act
//        ResponseEntity<ErrorResponse> response =
//                exceptionHandler.handleRuntimeException(ex, request);
//
//        // Assert
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertEquals("Erro inesperado", response.getBody().getMessage());
//    }
//
//    @Test
//    void handleAllExceptions_DeveRetornarMensagemGenerica() {
//        // Arrange
//        Exception ex = new Exception("Erro genérico");
//        WebRequest request = mock(WebRequest.class);
//        when(request.getDescription(false)).thenReturn("/api/user/register");
//
//        // Act
//        ResponseEntity<ErrorResponse> response =
//                exceptionHandler.handleAllExceptions(ex, request);
//
//        // Assert
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertEquals("Erro interno no servidor", response.getBody().getMessage());
//        assertEquals("/api/user/register", response.getBody().getPath());
//    }
//}