package com.rra.arts.arts_backend.exception;

import com.rra.arts.arts_backend.dto.StandardApiResponse;
import com.rra.arts.arts_backend.util.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

// ------------------------------
// 1. Handles 401 Unauthorized
// ------------------------------
@Component
public class SecurityExceptionHandlers implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         org.springframework.security.core.AuthenticationException authException)
            throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        StandardApiResponse<Object> apiResponse = StandardApiResponse.<Object>builder()
                .success(false)
                .message("You must be logged in to access this resource")
                .data(null)
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        response.getWriter().write(
                JsonUtil.toJson(apiResponse) // utility to convert object → JSON
        );
    }
}

