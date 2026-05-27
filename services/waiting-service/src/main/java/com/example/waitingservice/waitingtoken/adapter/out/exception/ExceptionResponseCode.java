package com.example.waitingservice.waitingtoken.adapter.out.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionResponseCode {
    CONCERT_VALIDATION_FAILED("유효하지 않은 콘서트 정보입니다");

    private final String errorMessage;
}
