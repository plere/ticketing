package com.example.reservationservice.tempreservation.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TempReservationErrorCode {
    RESERVATION_BAD_REQUEST_ERROR("정상적인 예매 요청이 아닙니다");

    private final String errorMessage;
}
