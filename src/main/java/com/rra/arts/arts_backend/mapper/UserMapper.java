package com.rra.arts.arts_backend.mapper;


import com.rra.arts.arts_backend.dto.UserResponse;
import com.rra.arts.arts_backend.model.User;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .departmentId(
                        user.getDepartment() != null ? user.getDepartment().getId() : null
                )
                .departmentName(
                        user.getDepartment() != null ? user.getDepartment().getName() : null
                )
                .isActive(user.getIsActive())
                .build();
    }
}
