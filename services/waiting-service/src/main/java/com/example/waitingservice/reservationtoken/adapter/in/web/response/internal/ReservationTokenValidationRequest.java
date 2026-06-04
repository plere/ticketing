package com.example.waitingservice.reservationtoken.adapter.in.web.response.internal;

import com.example.waitingservice.reservationtoken.model.ReservationToken;

public record ReservationTokenValidationRequest(
    ReservationToken token
) {
}
