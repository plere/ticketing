package com.example.waitingservice.reservationtoken.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationTokenResponseCode {
    CREATED_RESERVATION_TOKEN("예매 토큰 생성완료");

    private final String errorMessage;
}
