//package com.impostoCalc.security.test;
//
//import com.impostoCalc.dtos.Role;
//import com.impostoCalc.model.User;
//import com.impostoCalc.repository.UserRepository;
//import com.impostoCalc.security.SecurityFilter;
//import com.impostoCalc.security.TokenService;
//import jakarta.servlet.http.HttpServletRequest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class SecurityFilterTest {
//
//    @Mock
//    private TokenService tokenService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private HttpServletRequest request;
//
//    @InjectMocks
//    private SecurityFilter securityFilter;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        SecurityContextHolder.clearContext(); // Limpa o contexto de segurança antes de cada teste
//    }
//
//    @Test
//    void testAuthenticateRequest_WithValidTokenAndAdminRole() {
//        // Arrange
//        String token = "validToken";
//        String username = "adminUser";
//        User user = new User(1, username, "password", Role.ADMIN);
//
//        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
//        when(tokenService.validateToken(token)).thenReturn(username);
//        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
//
//        // Act
//        securityFilter.authenticateRequest(request);
//
//        // Assert
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        assertNotNull(authentication);
//        assertTrue(authentication.isAuthenticated());
//        assertEquals(user, authentication.getPrincipal());
//        assertEquals(2, authentication.getAuthorities().size()); // ROLE_ADMIN and ROLE_USER
//    }
//
//    @Test
//    void testAuthenticateRequest_WithValidTokenAndUserRole() {
//        // Arrange
//        String token = "validToken";
//        String username = "normalUser";
//        User user = new User(2, username, "password", Role.USER);
//
//        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
//        when(tokenService.validateToken(token)).thenReturn(username);
//        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
//
//        // Act
//        securityFilter.authenticateRequest(request);
//
//        // Assert
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        assertNotNull(authentication);
//        assertTrue(authentication.isAuthenticated());
//        assertEquals(user, authentication.getPrincipal());
//        assertEquals(1, authentication.getAuthorities().size()); // ROLE_USER
//    }
//
//    @Test
//    void testAuthenticateRequest_WithInvalidToken() {
//        // Arrange
//        String token = "invalidToken";
//
//        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
//        when(tokenService.validateToken(token)).thenReturn(null);
//
//        // Act
//        securityFilter.authenticateRequest(request);
//
//        // Assert
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        assertNull(authentication);
//    }
//
//    @Test
//    void testAuthenticateRequest_WithNoAuthorizationHeader() {
//        // Arrange
//        when(request.getHeader("Authorization")).thenReturn(null);
//
//        // Act
//        securityFilter.authenticateRequest(request);
//
//        // Assert
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        assertNull(authentication);
//    }
//
//    @Test
//    void testAuthenticateRequest_UserNotFound() {
//        // Arrange
//        String token = "validToken";
//        String username = "nonExistentUser";
//
//        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
//        when(tokenService.validateToken(token)).thenReturn(username);
//        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> securityFilter.authenticateRequest(request));
//        assertEquals("User Not Found", exception.getMessage());
//    }
//}