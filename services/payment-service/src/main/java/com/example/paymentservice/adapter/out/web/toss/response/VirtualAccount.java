package com.example.paymentservice.adapter.out.web.toss.response;

public record VirtualAccount(
    String accountType,
    String accountNumber,
    String bankCode,
    String customerName,
    String dueDate,
    String refundStatus,
    boolean expired,
    String settlementStatus,
    RefundReceiveAccount refundReceiveAccount,
    String secret
) {
}
