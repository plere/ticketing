package com.example.waitingservice.reservationtoken.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationTokenResponseCode {
    CREATED_RESERVATION_TOKEN("예매 토큰 생성완료"),
    CHECK_RESERVATION_TOKEN_VALIDATION("예매 토큰 유효성 체크");

    private final String errorMessage;
}
