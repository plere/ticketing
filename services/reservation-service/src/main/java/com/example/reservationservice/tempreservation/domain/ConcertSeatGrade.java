package com.example.reservationservice.tempreservation.domain;

import lombok.Builder;

@Builder
public record ConcertSeatGrade(
    Long id,
    String name,
    Integer price
) {
}
