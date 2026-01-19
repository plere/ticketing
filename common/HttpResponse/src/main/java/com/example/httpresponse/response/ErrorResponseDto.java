package com.example.httpresponse.response;

import org.springframework.http.ResponseEntity;

public record ErrorResponseDto(String errorMessage) {
    public static ResponseEntity<ResponseDto<ErrorResponseDto>> from(int status, String code, String errorMessage) {
        return ResponseDto.from(status, code, new ErrorResponseDto(errorMessage));
    }
}
