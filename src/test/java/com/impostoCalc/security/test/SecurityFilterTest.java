//package com.impostoCalc.security.test;
//
//import com.impostoCalc.dtos.Role;
//import com.impostoCalc.model.User;
//import com.impostoCalc.repository.UserRepository;
//import com.impostoCalc.security.SecurityFilter;
//import com.impostoCalc.security.TokenService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.test.util.ReflectionTestUtils;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
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
//    @Mock
//    private HttpServletResponse response;
//
//    @Mock
//    private FilterChain filterChain;
//
//    @InjectMocks
//    private SecurityFilter securityFilter;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        ReflectionTestUtils.setField(tokenService, "secret", "secret-test");
//    }
//
//    @Test
//    void testDoFilterInternal_WithValidTokenAndAdminRole() throws Exception {
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
//        invokeDoFilterInternal(request, response, filterChain);
//
//        // Assert
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        assertNotNull(authentication);
//        assertTrue(authentication.isAuthenticated());
//        assertEquals(username, authentication.getPrincipal());
//        assertEquals(1, authentication.getAuthorities().size());
//        assertEquals("ROLE_ADMIN", authentication.getAuthorities().iterator().next().getAuthority());
//        verify(filterChain, times(1)).doFilter(request, response);
//    }
//
//    @Test
//    void testDoFilterInternal_WithInvalidToken() throws Exception {
//        // Arrange
//        String token = "invalidToken";
//        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
//        when(tokenService.validateToken(token)).thenReturn(null);
//
//        // Act
//        invokeDoFilterInternal(request, response, filterChain);
//
//        // Assert
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        assertNull(authentication);
//        verify(filterChain, times(1)).doFilter(request, response);
//    }
//
//    @Test
//    void testDoFilterInternal_WithNoAuthorizationHeader() throws Exception {
//        // Arrange
//        when(request.getHeader("Authorization")).thenReturn(null);
//
//        // Act
//        invokeDoFilterInternal(request, response, filterChain);
//
//        // Assert
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        assertNull(authentication);
//        verify(filterChain, times(1)).doFilter(request, response);
//    }
//
//    @Test
//    void testDoFilterInternal_UserNotFound() throws Exception {
//        // Arrange
//        String token = "validToken";
//        String username = "nonExistentUser";
//
//        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
//        when(tokenService.validateToken(token)).thenReturn(username);
//        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
//
//        // Act
//        invokeDoFilterInternal(request, response, filterChain);
//
//        // Assert
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        assertNull(authentication);
//        verify(filterChain, times(1)).doFilter(request, response);
//    }
//
//
//    private void invokeDoFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws Exception {
//        Method method = SecurityFilter.class.getDeclaredMethod("doFilterInternal", HttpServletRequest.class, HttpServletResponse.class, FilterChain.class);
//        method.setAccessible(true); // Permite acesso ao método protegido
//        method.invoke(securityFilter, request, response, filterChain);
//    }
//}



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