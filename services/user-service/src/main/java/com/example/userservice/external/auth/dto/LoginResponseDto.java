package com.example.userservice.external.auth.dto;

import lombok.Builder;

@Builder
public record LoginResponseDto(
    String access_token,
    String refresh_token,
    String scope,
    String token_type,
    String expires_in
) {
}
