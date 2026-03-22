package com.example.paymentservice.domain;

public record PaymentExecutionResult(
    String paymentKey,
    String orderId,
    PaymentExtraDetails extraDetails,
    PaymentFailure failure,
    Boolean isSuccess,
    Boolean isFailure,
    Boolean isUnknown,
    Boolean isRetryable

) {
    public PaymentExecutionResult(String paymentKey, String orderId, PaymentExtraDetails extraDetails,
                                  PaymentFailure failure, Boolean isSuccess, Boolean isFailure, Boolean isUnknown,
                                  Boolean isRetryable) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.extraDetails = extraDetails;
        this.failure = failure;
        this.isSuccess = isSuccess;
        this.isFailure = isFailure;
        this.isUnknown = isUnknown;
        this.isRetryable = isRetryable;

        if (!(isSuccess || isFailure || isUnknown)) {
            throw new IllegalArgumentException(
                "결제 (orderId: " + orderId + ") 는 올바르지 않은 결제 상태입니다.");
        }
    }

    public PaymentStatus paymentStatus() {
        if (isSuccess) {
            return PaymentStatus.SUCCESS;
        } else if (isFailure) {
            return PaymentStatus.FAILURE;
        } else if (isUnknown) {
            return PaymentStatus.UNKNOWN;
        } else {
            throw new IllegalStateException(
                "결제 (orderId: " + orderId + ") 는 올바르지 않은 결제 상태입니다.");
        }
    }
}
