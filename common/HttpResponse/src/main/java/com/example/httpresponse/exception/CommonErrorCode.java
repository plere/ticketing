package com.example.httpresponse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorCode {
    TEMPORARY_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요"),
    NO_CONTENT("정보를 찾을 수 없습니다"),
    ARGUMENT_ERROR("잘못된 입력입니다");

    private final String errorMessage;
}
