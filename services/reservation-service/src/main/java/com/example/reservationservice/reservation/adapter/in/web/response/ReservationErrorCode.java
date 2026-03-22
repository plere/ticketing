package com.example.reservationservice.reservation.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationErrorCode {
    PAY_CHECKOUT_ERROR("결제를 시작할 수 없습니다"),
    EXECUTE_PAYMENT_ERROR("결제를 완료할 수 없습니다");

    private final String errorMessage;
}
