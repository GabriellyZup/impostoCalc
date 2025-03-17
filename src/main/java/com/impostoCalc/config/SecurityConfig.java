package com.impostoCalc.config;

import com.impostoCalc.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Desabilita CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api/user/register", "/api/user/login").permitAll() // Endpoints públicos
                        .requestMatchers("/api/tipos").hasAnyRole("USER", "ADMIN") // USER e ADMIN podem consultar tipos de impostos
                        .requestMatchers("/api/tipos/**").hasRole("ADMIN") // Apenas ADMIN pode criar e excluir tipos de impostos
                        .requestMatchers("/calculo").hasAnyRole("USER", "ADMIN") // USER e ADMIN podem calcular impostos
                        .anyRequest().authenticated() // Todos os outros endpoints exigem autenticação
                )
                .httpBasic(httpBasic -> httpBasic.disable()); // Substituímos o método obsoleto
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}