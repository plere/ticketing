package com.example.paymentservice.adapter.out.web.toss.response;

public record CashReceipt(
    String type,
    String receiptKey,
    String issueNumber,
    String receiptUrl,
    int amount,
    int taxFreeAmount
) {
}
