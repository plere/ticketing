package com.example.concertservice.concert.application.service.exception.seat;

import com.example.httpresponse.exception.CustomRuntimeException;
import org.springframework.http.HttpStatus;

public class HoldConcertSeatValidationException extends CustomRuntimeException {
    private static final HttpStatus status = HttpStatus.BAD_REQUEST;
    private static final String message = "좌석을 선점할 수 없는 상태입니다";
    private static final String code = "HOLD_CONCERT_SEAT_STATE";

    public HoldConcertSeatValidationException() {
        super(status.value(), code, message);
    }
}
