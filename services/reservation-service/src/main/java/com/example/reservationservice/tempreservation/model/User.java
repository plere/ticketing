package com.example.reservationservice.tempreservation.model;

import lombok.Builder;

@Builder
public record User(
    Long id,
    String email
) {
}
