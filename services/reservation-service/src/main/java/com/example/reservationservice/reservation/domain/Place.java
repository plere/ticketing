package com.example.reservationservice.reservation.domain;

import lombok.Builder;

@Builder
public record Place(
    Long id,
    String name,
    String address
) {
}
