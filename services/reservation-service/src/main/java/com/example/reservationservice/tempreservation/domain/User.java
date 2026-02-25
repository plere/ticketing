package com.example.reservationservice.tempreservation.domain;

import lombok.Builder;

@Builder
public record User(
    Long id,
    String email
) {
}
