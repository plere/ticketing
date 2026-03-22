package com.example.paymentservice.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PaymentMethod {
    EASY_PAY("간편결제");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public static PaymentMethod get(String method) {
        return Arrays.stream(values())
            .filter(value -> value.description.equals(method))
            .findAny()
            .orElseThrow(
                () -> new IllegalArgumentException("Payment Method (methpd: " + method + ") 는 올바르이 않은 결제 방법입니다."));
    }
}
