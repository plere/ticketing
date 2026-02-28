package com.example.reservationservice.reservation.domain;

import lombok.Builder;

@Builder
public record ConcertSeatGrade(
    Long id,
    String name,
    Integer price
) {
}
