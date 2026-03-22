package com.example.paymentservice.adapter.in.web.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentResponseErrorCode {
    EXECUTION_PAYMENT_VALIDATION_ERROR("잘못된 결제요청 입니다");

    private final String errorMessage;
}
