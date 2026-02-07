package com.example.userservice.controller.dto;

import com.example.userservice.model.User;
import lombok.Builder;

@Builder
public record UserMeResponseDto(
    Long id,
    String email,
    String name
) {
    public static UserMeResponseDto from(User user) {
        return UserMeResponseDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .build();
    }
}
