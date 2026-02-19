package com.rra.arts.arts_backend.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rra.arts.arts_backend.dto.StandardApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Handles 403 Forbidden responses for authenticated users who lack permissions.
 * Provides a clear JSON response with guidance, timestamp, and requested path.
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public JwtAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        // Build structured response
        StandardApiResponse<Object> apiResponse = StandardApiResponse.<Object>builder()
                .success(false)
                .message("You do not have permission to access this resource. " +
                        "Please contact your administrator if you think this is an error.")
                .data(null)
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        // Serialize directly using ObjectMapper
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
