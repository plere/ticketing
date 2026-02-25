package com.example.reservationservice.tempreservation.domain;

import lombok.Builder;

import java.util.Set;

@Builder
public record TempReservation(
    Long id,
    long userId,
    long concertId,
    long roundId,
    Set<Long> seatIds
) {
}
