package com.example.httpresponse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorCode {
    NO_CONTENT("정보를 찾을 수 없습니다"),
    ARGUMENT_ERROR("잘못된 입력입니다");

    private final String errorMessage;
}
