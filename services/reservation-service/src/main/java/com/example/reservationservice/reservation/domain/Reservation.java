package com.example.reservationservice.reservation.domain;

import lombok.Builder;

import java.util.Set;

@Builder
public record Reservation(
    Long id,
    Long userId,
    String paymentKey,
    String orderId,
    Long concertId,
    String concertName,
    Long roundId,
    String amount,
    Set<Long> seatIds,
    ReservationStatus status
) {
}
