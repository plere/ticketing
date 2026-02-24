package com.example.concertservice.common.exception;

import com.example.concertservice.concert.application.service.exception.seat.ConcertSeatLockingFailureException;
import com.example.concertservice.concert.application.service.exception.seat.HoldConcertSeatValidationException;
import com.example.concertservice.concert.application.service.exception.seat.ReleaseHoldSeatsValidationException;
import com.example.httpresponse.response.ErrorResponseDto;
import com.example.httpresponse.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.concertservice.concert.adapter.in.web.response.internal.InternalConcertSeatErrorResponseCode.*;

@RestControllerAdvice
public class InternalConcertExceptionAdvice {
    @ExceptionHandler(ConcertSeatLockingFailureException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto>> concertSeatLockingFailureException(ConcertSeatLockingFailureException exception) {
        return ErrorResponseDto.from(HttpStatus.CONFLICT.value(), HOLD_SEATS_ERROR.name(), HOLD_SEATS_ERROR.getErrorMessage());
    }

    @ExceptionHandler(HoldConcertSeatValidationException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto>> holdConcertSeatValidationException(HoldConcertSeatValidationException exception) {
        return ErrorResponseDto.from(HttpStatus.BAD_REQUEST.value(), HOLD_SEAT_VALID_ERROR.name(), HOLD_SEAT_VALID_ERROR.getErrorMessage());
    }

    @ExceptionHandler(ReleaseHoldSeatsValidationException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto>> releaseHoldSeatsValidationException(ReleaseHoldSeatsValidationException exception) {
        return ErrorResponseDto.from(HttpStatus.BAD_REQUEST.value(), RELEASE_HOLD_SEAT_VALID_ERROR.name(), RELEASE_HOLD_SEAT_VALID_ERROR.getErrorMessage());
    }
}
