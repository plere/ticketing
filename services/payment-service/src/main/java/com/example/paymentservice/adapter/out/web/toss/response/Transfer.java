package com.example.paymentservice.adapter.out.web.toss.response;

public record Transfer(
    String bankCode,
    String settlementStatus
) {
}
