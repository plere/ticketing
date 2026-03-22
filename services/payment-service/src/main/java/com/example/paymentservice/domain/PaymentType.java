package com.example.paymentservice.domain;

import lombok.Getter;

@Getter
public enum PaymentType {
    NORMAL("일반 결제");

    private final String description;

    PaymentType(String description) {
        this.description = description;
    }
}
