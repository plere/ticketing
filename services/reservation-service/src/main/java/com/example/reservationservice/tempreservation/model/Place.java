package com.example.reservationservice.tempreservation.model;

import lombok.Builder;

@Builder
public record Place(
    Long id,
    String name,
    String address
) {
}
