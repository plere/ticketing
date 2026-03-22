package com.example.paymentservice.adapter.out.web.toss.response;

public record Card(
    int amount,
    String issuerCode,
    String acquirerCode,
    String number,
    int installmentPlanMonths,
    String approveNo,
    boolean useCardPoint,
    String cardType,
    String ownerType,
    String acquireStatus,
    boolean isInterestFree,
    String interestPayer
) {
}
