package com.example.reservationservice.common.exception;

import com.example.httpresponse.response.ErrorResponseDto;
import com.example.httpresponse.response.ResponseDto;
import com.example.reservationservice.tempreservation.adapter.out.exception.LockHoldSeatException;
import com.example.reservationservice.tempreservation.application.service.exception.HoldSeatException;
import com.example.reservationservice.tempreservation.application.service.exception.TempReservationValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.reservationservice.tempreservation.adapter.in.TempReservationErrorCode.RESERVATION_BAD_REQUEST_ERROR;
import static com.example.reservationservice.tempreservation.adapter.in.TempReservationErrorCode.SEATS_IS_ALREADY_RESERVED;

@RestControllerAdvice
public class TempReservationExceptionAdvice {
    @ExceptionHandler(HoldSeatException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto>> holdSeatException(HoldSeatException exception) {
        return ErrorResponseDto.from(HttpStatus.CONFLICT.value(), SEATS_IS_ALREADY_RESERVED.name(), SEATS_IS_ALREADY_RESERVED.getErrorMessage());
    }

    @ExceptionHandler(LockHoldSeatException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto>> lockHoldSeatException(LockHoldSeatException exception) {
        return ErrorResponseDto.from(HttpStatus.CONFLICT.value(), SEATS_IS_ALREADY_RESERVED.name(), SEATS_IS_ALREADY_RESERVED.getErrorMessage());
    }

    @ExceptionHandler(TempReservationValidException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto>> tempReservationValidException(TempReservationValidException exception) {
        return ErrorResponseDto.from(HttpStatus.BAD_REQUEST.value(), RESERVATION_BAD_REQUEST_ERROR.name(), RESERVATION_BAD_REQUEST_ERROR.getErrorMessage());
    }
}
