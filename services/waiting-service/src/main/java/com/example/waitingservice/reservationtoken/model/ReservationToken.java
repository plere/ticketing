package com.example.waitingservice.reservationtoken.model;

import lombok.Builder;

@Builder
public record ReservationToken(
    long id,
    String token
) {
}
