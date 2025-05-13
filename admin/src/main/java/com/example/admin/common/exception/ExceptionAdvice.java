package com.example.admin.common.exception;

import com.example.admin.common.response.ErrorResponseDto;
import com.example.admin.common.response.ResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseDto<ErrorResponseDto> customRuntimeException(CustomRuntimeException exception) {
        return ErrorResponseDto.from(exception.getStatus(), exception.getCode(), exception.getMessage());
    }
}
