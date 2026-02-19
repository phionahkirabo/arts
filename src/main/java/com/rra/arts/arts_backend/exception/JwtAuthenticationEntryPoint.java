package com.rra.arts.arts_backend.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rra.arts.arts_backend.dto.StandardApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Build structured response
        StandardApiResponse<Object> apiResponse = StandardApiResponse.<Object>builder()
                .success(false)
                .message("You must be logged in to access this resource. Please log in and try again.")
                .data(null)
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        // Serialize directly using ObjectMapper
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
