package com.example.httpresponse.exception;

import com.example.httpresponse.response.ErrorResponseDto;
import com.example.httpresponse.response.ResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseDto<ErrorResponseDto> customRuntimeException(CustomRuntimeException exception) {
        return ErrorResponseDto.from(exception.getStatus(), exception.getCode(), exception.getMessage());
    }
}
