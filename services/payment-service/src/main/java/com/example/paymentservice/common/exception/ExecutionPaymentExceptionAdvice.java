package com.example.paymentservice.common.exception;

import com.example.httpresponse.response.ErrorResponseDto;
import com.example.httpresponse.response.ResponseDto;
import com.example.paymentservice.service.exception.ExecutePaymentValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.paymentservice.adapter.in.web.response.PaymentResponseErrorCode.EXECUTION_PAYMENT_VALIDATION_ERROR;

@RestControllerAdvice
public class ExecutionPaymentExceptionAdvice {
    @ExceptionHandler(ExecutePaymentValidationException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto>> executePaymentValidationException(ExecutePaymentValidationException exception) {
        return ErrorResponseDto.from(HttpStatus.BAD_REQUEST.value(), EXECUTION_PAYMENT_VALIDATION_ERROR.name(), EXECUTION_PAYMENT_VALIDATION_ERROR.getErrorMessage());
    }
}
