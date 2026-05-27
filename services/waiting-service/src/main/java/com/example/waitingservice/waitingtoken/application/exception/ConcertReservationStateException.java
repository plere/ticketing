package com.example.waitingservice.waitingtoken.application.exception;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class ConcertReservationStateException extends RuntimeException {
    private final long concertId;
    private final long roundId;
}
