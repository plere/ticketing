package com.example.paymentservice.domain;

import lombok.Builder;

@Builder
public record ExecutePaymentCommand(
    String orderId,
    String paymentKey,
    String amount
) {
}
