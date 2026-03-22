package com.example.reservationservice.reservation.domain;

import lombok.Builder;

@Builder
public record ExecutePaymentCommand(
    Long id,
    String paymentKey,
    String orderId,
    String amount,
    Long userId
) {
}
