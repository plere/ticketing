package com.example.chekcreservationtoken.external.reservationtoken;

import lombok.Builder;

@Builder
public record ReservationToken(
    String id,
    String token
) {
}
