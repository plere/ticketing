package com.example.reservationservice.tempreservation.model;

import lombok.Builder;

@Builder
public record ConcertSeatGrade(
    Long id,
    String name,
    Integer price
) {
}
