package com.example.paymentservice.adapter.out.web.toss.response;

public record Cancel(
    int cancelAmount,
    String cancelReason,
    int taxFreeAmount,
    int taxExemptionAmount,
    int refundableAmount,
    int easyPayDiscountAmount,
    String canceledAt,
    String transactionKey,
    String receiptKey,
    boolean isPartialCancelable
) {
}
