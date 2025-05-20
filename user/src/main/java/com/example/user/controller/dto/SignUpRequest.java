package com.example.user.controller.dto;

import com.example.user.model.User;
import com.example.user.service.PasswordEncryptor;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
    @NotBlank
    String email,
    @NotBlank
    String name,
    @NotBlank
    String password,
    String address,
    String phone
) {
    public User toModel(PasswordEncryptor passwordEncryptor) {
        return User.builder()
            .email(email)
            .name(name)
            .password(passwordEncryptor.encrypt(password))
            .address(address)
            .phone(phone)
            .build();
    }
}
