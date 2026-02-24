package com.example.reservationservice.tempreservation.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertRound(
    Long id,
    LocalDateTime startDateTime,
    Integer sequenceNumber
) {
}
