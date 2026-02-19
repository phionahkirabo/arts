package com.rra.arts.arts_backend.config;

import java.util.List;

public class PublicEndpoints {

    public static final List<String> ENDPOINTS = List.of(
            // -------------------------
            //   Public Authentication URLs
            // -------------------------
            "/api/users/signup",
            "/api/users/login",
            "/api/users/forgot-password",
            "/api/users/reset-password/otp",
            "/api/users/reset-password",
            "/api/users/verify-email",

            // -------------------------
            //   Swagger / API Docs URLs
            // -------------------------
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/v3/api-docs/**",       // Springdoc default
            "/api-documentation/**",
            "/api-docs.yaml",

            // -------------------------
            //   Public Content URLs (if you have public resources)
            // -------------------------
            "/api/public/items",
            "/api/public/categories",
            "/api/public/posts",
            "/api/public/blogs",
            "/api/public/pictures"
    );
}
