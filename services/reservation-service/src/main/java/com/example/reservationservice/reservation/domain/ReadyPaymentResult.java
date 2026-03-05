package com.example.reservationservice.reservation.domain;

import lombok.Builder;

@Builder
public record ReadyPaymentResult(
    ReadyPaymentStatus status
) {
    public static ReadyPaymentResult createSuccessResult() {
        return ReadyPaymentResult.builder()
            .status(ReadyPaymentStatus.SUCCESS)
            .build();
    }

    public static ReadyPaymentResult createFailResult() {
        return ReadyPaymentResult.builder()
            .status(ReadyPaymentStatus.FAIL)
            .build();
    }
}
