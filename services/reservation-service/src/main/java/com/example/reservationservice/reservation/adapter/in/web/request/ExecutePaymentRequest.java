package com.example.reservationservice.reservation.adapter.in.web.request;

import com.example.checkauth.UserToken;
import com.example.reservationservice.reservation.domain.ExecutePaymentCommand;

public record ExecutePaymentRequest(
    Long id,
    String paymentKey,
    String orderId,
    String amount
) {
    public ExecutePaymentCommand toCommand(UserToken userToken) {
        return ExecutePaymentCommand.builder()
            .id(id)
            .paymentKey(paymentKey)
            .orderId(orderId)
            .amount(amount)
            .userId(userToken.getId())
            .build();
    }
}
