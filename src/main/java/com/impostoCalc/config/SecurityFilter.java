//package com.impostoCalc.config;
//
//import com.impostoCalc.repository.UserRepository;
//import com.impostoCalc.config.TokenService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class SecurityFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
//    }//construtor criado para atender a classe de teste
//
//
//    @Override
//    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        var token = recoverToken(request);
//        if (token != null) {
//            var username = tokenService.validateToken(token);
//            UserDetails userDetails = userRepository.findByUsername(username);
//
//            if (userDetails != null) {
//                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private String recoverToken(HttpServletRequest request) {
//        var authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return null;
//        }
//        return authHeader.replace("Bearer ", "");
//    }
//}