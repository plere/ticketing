package com.example.reservationservice.common.exception;

import com.example.httpresponse.response.ErrorResponseDto;
import com.example.httpresponse.response.ResponseDto;
import com.example.reservationservice.reservation.application.service.exception.FailExecutePaymentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.reservationservice.reservation.adapter.in.web.response.ReservationErrorCode.EXECUTE_PAYMENT_ERROR;

@RestControllerAdvice
public class PaymentExceptionAdvice {
    @ExceptionHandler(FailExecutePaymentException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto>> failExecutePaymentException(FailExecutePaymentException exception) {
        return ErrorResponseDto.from(HttpStatus.SERVICE_UNAVAILABLE.value(), EXECUTE_PAYMENT_ERROR.name(), EXECUTE_PAYMENT_ERROR.getErrorMessage());
    }
}
