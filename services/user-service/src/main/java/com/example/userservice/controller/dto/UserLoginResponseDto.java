package com.example.userservice.controller.dto;

import com.example.userservice.external.auth.dto.LoginResponseDto;
import lombok.Builder;

@Builder
public record UserLoginResponseDto(
    String access_token,
    String refresh_token,
    String scope,
    String expires_in
) {
    public static UserLoginResponseDto from(LoginResponseDto loginDto) {
        return UserLoginResponseDto.builder()
            .access_token(loginDto.access_token())
            .refresh_token(loginDto.refresh_token())
            .scope(loginDto.scope())
            .expires_in(loginDto.expires_in())
            .build();
    }
}
