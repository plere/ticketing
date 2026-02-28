package com.example.reservationservice.reservation.domain;

import lombok.Builder;

@Builder
public record User(
    Long id,
    String email
) {
}
