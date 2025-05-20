package com.example.httpresponse.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {
    private final String errorMessage;

    public static ResponseDto<ErrorResponseDto> from(int status, String code, String errorMessage) {
        return ResponseDto.from(status, code, new ErrorResponseDto(errorMessage));
    }
}
