package com.example.paymentservice.domain;

public record PaymentFailure(
    String errorCode,
    String message
) {
}
