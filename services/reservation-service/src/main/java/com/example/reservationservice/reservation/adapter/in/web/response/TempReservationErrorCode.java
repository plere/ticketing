package com.example.reservationservice.reservation.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TempReservationErrorCode {
    RESERVATION_BAD_REQUEST_ERROR("정상적인 예매 요청이 아닙니다"),
    SEATS_IS_ALREADY_RESERVED("이미 선점된 좌석입니다");

    private final String errorMessage;
}
