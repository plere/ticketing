package com.example.paymentservice.domain;

import lombok.Builder;

import static com.example.paymentservice.domain.PaymentStatus.NOT_STARTED;

@Builder
public record ReadyPaymentEventCommand(
    Long userId,
    String orderId,
    String orderName
) {
    public PaymentEvent toPaymentEvent() {
        return PaymentEvent.builder()
            .userId(userId)
            .orderId(orderId)
            .orderName(orderName)
            .status(NOT_STARTED)
            .isPaymentDone(false)
            .build();
    }
}
