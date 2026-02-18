package com.rra.arts.arts_backend.service.impl;

import com.rra.arts.arts_backend.dto.*;
import com.rra.arts.arts_backend.exception.BadRequestException;
import com.rra.arts.arts_backend.exception.DuplicateResourceException;
import com.rra.arts.arts_backend.mapper.UserMapper;
import com.rra.arts.arts_backend.model.User;
import com.rra.arts.arts_backend.model.enums.UserRole;
import com.rra.arts.arts_backend.repository.UsersRepository;
//import com.rra.arts.arts_backend.repository.DepartmentRepository;
import com.rra.arts.arts_backend.service.JWTService;
import com.rra.arts.arts_backend.service.UserService;
import com.rra.arts.arts_backend.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service

public class UserServiceImpl implements UserService {
final private UsersRepository usersRepository;

final private JWTService jwtService;
final private PasswordEncoder passwordEncoder;
    public UserServiceImpl(UsersRepository usersRepository, JWTService jwtService, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse signup(SignupRequest request) {



        usersRepository.findByEmail(request.getEmail()).ifPresent(existingUser -> {
            throw new DuplicateResourceException("Email already in use: " + request.getEmail());
        });

        User newUser = new User();
        newUser.setFullName(request.getFullName());
        newUser.setEmail(request.getEmail());
        newUser.setPhone(request.getPhone());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setIsActive(true);
        User savedUser = usersRepository.save(newUser);


        return UserMapper.toResponse(savedUser);


    }

    @Override
    public Map<String, Object> login(LoginRequest request) {
        // 1️⃣ Find user by email
        User user = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        // 2️⃣ Check if user is active
        if (user.getIsActive() == null || !user.getIsActive()) {
            throw new BadRequestException("User account is inactive");
        }

        // 3️⃣ Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        // 4️⃣ Generate JWT tokens
        UserPrincipal principal = new UserPrincipal(user);
        String accessToken = jwtService.generateAccessToken(principal);
        String refreshToken = jwtService.generateRefreshToken(principal);

        // 5️⃣ Convert user to response DTO using mapper
        UserResponse userResponse = UserMapper.toResponse(user);

        // 6️⃣ Return map with user + tokens
        return Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken,
                "user", userResponse

        );
    }

    @Override
    public void changePassword(Long userId, ChangePasswordRequest request) {

    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {

    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
