package com.example.paymentservice.domain;

import java.time.LocalDateTime;

public record PaymentExtraDetails(
    PaymentType type,
    PaymentMethod method,
    LocalDateTime approvedAt,
    String orderName,
    PSPConfirmationStatus pspConfirmationStatus,
    Long totalAmount,
    String pspRawData
) {
}
