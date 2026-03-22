package com.example.paymentservice.adapter.out.web.toss.response;

public record EasyPay(
    String provider,
    int amount,
    int discountAmount
) {
}
