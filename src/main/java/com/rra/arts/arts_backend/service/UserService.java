package com.rra.arts.arts_backend.service;

import com.rra.arts.arts_backend.dto.*;
import com.rra.arts.arts_backend.model.User;

import java.util.Map;

public interface UserService {

    // Returns the created user DTO
    UserResponse signup(SignupRequest request);

    // Returns access and refresh tokens
    Map<String, Object> login(LoginRequest request);

    // Returns nothing, just performs the operation
    void changePassword(Long userId, ChangePasswordRequest request);

    // Returns nothing, just triggers password reset workflow
    void forgotPassword(ForgotPasswordRequest request);

    // Returns the User entity (can be used internally)
    User findByEmail(String email);
}
