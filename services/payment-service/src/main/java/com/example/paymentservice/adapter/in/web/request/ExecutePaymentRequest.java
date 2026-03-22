package com.example.paymentservice.adapter.in.web.request;

import com.example.paymentservice.domain.ExecutePaymentCommand;

public record ExecutePaymentRequest(
    String orderId,
    String paymentKey,
    String amount
) {
    public ExecutePaymentCommand toCommand() {
        return ExecutePaymentCommand.builder()
            .orderId(orderId)
            .paymentKey(paymentKey)
            .amount(amount)
            .build();
    }
}
