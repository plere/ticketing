package com.example.waitingservice.reservationtoken.model;

import lombok.Builder;

@Builder
public record ReservationToken(
    String id,
    String token
) {
}
