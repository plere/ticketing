package com.example.concertservice.concert.application.service.exception.seat;

import com.example.httpresponse.exception.CustomRuntimeException;
import org.springframework.http.HttpStatus;

public class ConcertSeatLockingFailureException extends CustomRuntimeException {
    private static final HttpStatus status = HttpStatus.CONFLICT;
    private static final String message = "좌석을 선점할 수 없습니다";
    private static final String code = "HOLD_CONCERT_SEAT_LOCK_FAILURE";

    public ConcertSeatLockingFailureException() {
        super(status.value(), code, message);
    }
}
