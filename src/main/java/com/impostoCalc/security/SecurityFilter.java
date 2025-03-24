package com.impostoCalc.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.impostoCalc.dtos.Role;
import com.impostoCalc.model.User;
import com.impostoCalc.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    // Remova a injeção do UserRepository para evitar consultas desnecessárias ao banco de dados
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        var token = this.recoverToken(request);

        if (token != null) {
            // Valida o token e decodifica para obter as claims (incluindo a role)
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(tokenService.getSecret()))
                    .withIssuer("impostocalc")
                    .build()
                    .verify(token);

            String username = decodedJWT.getSubject();
            String role = decodedJWT.getClaim("role").asString(); // Obtém a role do token

            // Cria as autoridades com base na role do token
            List<GrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_" + role) // Ex: "ROLE_ADMIN"
            );

            // Define a autenticação no contexto do Spring Security
            var authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}

//@Component
//public class SecurityFilter extends OncePerRequestFilter {
//    @Autowired
//    TokenService tokenService;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        var token = this.recoverToken(request);
//        var username = tokenService.validateToken(token);
//
//        if(username != null){
//            User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User Not Found"));
//
//            Collection<? extends GrantedAuthority> authorities;
//            if (user.getRole() == Role.ADMIN) {
//                authorities = List.of(
//                        new SimpleGrantedAuthority("ROLE_ADMIN"),
//                        new SimpleGrantedAuthority("ROLE_USER")
//                );
//            } else {
//                authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
//            }
//
//            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private String recoverToken(HttpServletRequest request){
//        var authHeader = request.getHeader("Authorization");
//        if(authHeader == null) return null;
//        return authHeader.replace("Bearer ", "");
//    }
//}