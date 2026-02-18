package com.rra.arts.arts_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest extends AuthRequest {

    @NotBlank(message = "Full name is required")
    @Size(min = 3, max = 100, message = "Full name must be between 3 and 100 characters")
    @Schema(description = "Full name of the user", example = "John Doe", required = true)
    private String fullName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?\\d{9,15}$", message = "Phone number must be valid with 9-15 digits, optional '+' prefix")
    @Schema(description = "Phone number with optional '+' prefix", example = "+250788123456", required = true)
    private String phone;
}
