package com.example.paymentservice.adapter.in.web.request;

import com.example.paymentservice.domain.ReadyPaymentEventCommand;

public record ReadyPaymentRequest(
    Long userId,
    String orderName,
    String orderId,
    String amount
) {
    public ReadyPaymentEventCommand toCommand() {
        return ReadyPaymentEventCommand.builder()
            .userId(userId)
            .orderId(orderId)
            .orderName(orderName)
            .amount(amount)
            .build();
    }
}
