package com.example.userservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorResponseCode {
    DUPLICATED_EMAIL("이미 가입된 이메일입니다.");

    private final String errorMessage;
}
