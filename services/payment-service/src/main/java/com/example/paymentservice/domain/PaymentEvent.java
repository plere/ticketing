package com.example.paymentservice.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PaymentEvent(
    Long id,
    Long userId,
    String orderId,
    String orderName,
    String paymentKey,
    LocalDateTime approvedAt,
    PaymentStatus status,
    Boolean isPaymentDone
) {
}
