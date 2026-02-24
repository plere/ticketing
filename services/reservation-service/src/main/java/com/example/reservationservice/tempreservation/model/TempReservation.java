package com.example.reservationservice.tempreservation.model;

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
