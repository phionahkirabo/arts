package com.rra.arts.arts_backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "BearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // API info
                .info(new Info()
                        .title("ARTS - Audit Report Tracking System API")
                        .version("1.0.0")
                        .description("This API allows Internal Auditors to create audit reports, assign directors, and manage recommendations. All endpoints are secured via JWT Bearer tokens.")
                        .contact(new Contact()
                                .name("ARTS Team")
                                .email("support@arts-system.com")
                                .url("https://arts-system.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                )
                // Servers
                .servers(List.of(
                        new Server().url("http://localhost:8071").description("Local development server"),
                        new Server().url("https://api.arts-system.com").description("Production server")
                ))
                // Security
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
    }
}
