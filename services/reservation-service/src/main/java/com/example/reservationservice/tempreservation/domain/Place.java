package com.example.reservationservice.tempreservation.domain;

import lombok.Builder;

@Builder
public record Place(
    Long id,
    String name,
    String address
) {
}
