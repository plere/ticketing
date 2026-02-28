package com.example.reservationservice.reservation.domain;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    TEMP("임시 정보"),
    EXPIRED("임시 정보 만료"),
    PAY_REQUESTING("결제 요청 중"),
    PAY_EXECUTING("결제 승인 중"),
    RESERVED("예매 완료"),
    PAY_FAIL("결제 승인 실패");

    private final String message;

    ReservationStatus(String message) {
        this.message = message;
    }
}
