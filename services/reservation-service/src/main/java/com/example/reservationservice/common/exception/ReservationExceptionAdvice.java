package com.example.reservationservice.common.exception;

import com.example.httpresponse.response.ErrorResponseDto;
import com.example.httpresponse.response.ResponseDto;
import com.example.reservationservice.reservation.application.service.exception.PayCheckoutCallException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.reservationservice.reservation.adapter.in.web.response.ReservationErrorCode.PAY_CHECKOUT_ERROR;

@RestControllerAdvice
public class ReservationExceptionAdvice {
    @ExceptionHandler(PayCheckoutCallException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto>> payCheckoutCallException(PayCheckoutCallException exception) {
        return ErrorResponseDto.from(HttpStatus.BAD_GATEWAY.value(), PAY_CHECKOUT_ERROR.name(), PAY_CHECKOUT_ERROR.getErrorMessage());
    }
}
