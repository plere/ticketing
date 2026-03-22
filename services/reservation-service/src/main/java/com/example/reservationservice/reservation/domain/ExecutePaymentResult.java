package com.example.reservationservice.reservation.domain;

import lombok.Builder;

@Builder
public record ExecutePaymentResult(
    ExecutePaymentStatus status
) {
    public static ExecutePaymentResult createSuccessResult() {
        return ExecutePaymentResult.builder()
            .status(ExecutePaymentStatus.SUCCESS)
            .build();
    }

    public static ExecutePaymentResult createFailResult() {
        return ExecutePaymentResult.builder()
            .status(ExecutePaymentStatus.FAIL)
            .build();
    }
}
