package com.example.paymentservice.adapter.out.web.toss.response;

public record RefundReceiveAccount(
    String bankCode,
    String accountNumber,
    String holderName
) {
}
