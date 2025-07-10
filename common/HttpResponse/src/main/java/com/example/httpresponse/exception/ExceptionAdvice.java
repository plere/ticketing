package com.example.httpresponse.exception;

import com.example.httpresponse.response.ErrorResponseDto;
import com.example.httpresponse.response.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(ServerException.class)
    public ResponseDto<ErrorResponseDto> customRuntimeException(ServerException exception) {
        log.error("server error!!", exception);

        return ErrorResponseDto.from(exception.getStatus(), exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseDto<ErrorResponseDto> customRuntimeException(CustomRuntimeException exception) {
        return ErrorResponseDto.from(exception.getStatus(), exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto<ErrorResponseDto> customRuntimeException(MethodArgumentNotValidException exception) {
        return ErrorResponseDto.from(HttpStatus.BAD_REQUEST.value(), CommonErrorCode.ARGUMENT_ERROR.toString(), CommonErrorCode.ARGUMENT_ERROR.getErrorMessage());
    }
}
