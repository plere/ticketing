package com.example.waitingservice.waitingtoken.adapter.out.exception;

import com.example.httpresponse.response.ErrorResponseDto;
import com.example.httpresponse.response.ResponseDto;
import com.example.waitingservice.waitingtoken.application.exception.ConcertReservationStateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.waitingservice.waitingtoken.adapter.out.exception.ExceptionResponseCode.CONCERT_VALIDATION_FAILED;

@Slf4j
@RestControllerAdvice
public class WaitingTokenExceptionAdvice {
    @ExceptionHandler(ConcertReservationStateException.class)
    public ResponseEntity<ResponseDto<ErrorResponseDto>> concertReservationStateException(ConcertReservationStateException exception) {
        log.error("ConcertReservationStateException", exception);

        return ErrorResponseDto.from(HttpStatus.BAD_REQUEST.value(), CONCERT_VALIDATION_FAILED.toString(), CONCERT_VALIDATION_FAILED.getErrorMessage());
    }
}
