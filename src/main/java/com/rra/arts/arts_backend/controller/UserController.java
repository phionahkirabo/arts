package com.rra.arts.arts_backend.controller;

import com.rra.arts.arts_backend.dto.LoginRequest;
import com.rra.arts.arts_backend.dto.SignupRequest;
import com.rra.arts.arts_backend.dto.StandardApiResponse;
import com.rra.arts.arts_backend.dto.UserResponse;
import com.rra.arts.arts_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Signup a new user
     */
    @PostMapping("/signup")
    public StandardApiResponse<UserResponse> signup(
            @Valid @RequestBody SignupRequest request) {

        // Call the service to handle business logic
        UserResponse userResponse = userService.signup(request);

        // Wrap the result in StandardApiResponse
        return StandardApiResponse.success(userResponse, "User created successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<StandardApiResponse<Map<String, Object>>> login(
            @RequestBody LoginRequest request
    ) {
        Map<String, Object> result = userService.login(request); // contains user + tokens
        return ResponseEntity.ok(StandardApiResponse.success(result, "Login successful"));
    }
}
