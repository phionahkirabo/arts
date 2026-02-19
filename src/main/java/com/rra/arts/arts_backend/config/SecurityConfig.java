package com.rra.arts.arts_backend.config;

import com.rra.arts.arts_backend.exception.JwtAccessDeniedHandler;
import com.rra.arts.arts_backend.exception.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtAccessDeniedHandler accessDeniedHandler;
    private final JWTFilter jwtFilter;

    // -----------------------
    // Password encoder bean
    // -----------------------
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    // -----------------------
    // Security filter chain
    // -----------------------
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 1️⃣ Allow public endpoints
                        .requestMatchers(PublicEndpoints.ENDPOINTS.toArray(new String[0])).permitAll()
                        // 2️⃣ All other endpoints require authentication
                        .anyRequest().authenticated()
                )
                // 3️⃣ Add JWT filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // 4️⃣ Add security exception handlers
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                );

        return http.build();
    }
}
