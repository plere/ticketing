package com.example.paymentservice.adapter.out.web.toss.response;

public record MobilePhone(
    String customerMobilePhone,
    String settlementStatus,
    String receiptUrl
) {
}
