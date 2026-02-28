package com.example.reservationservice.reservation.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TempReservationResponseCode {
    IS_EXIST_TEMP_RESERVATION("임시 예매 정보가 존재하는지 확인"),
    GET_TEMP_RESERVATION("임시 예매 정보 조회"),
    CREATED_TEMP_RESERVATION("임시 예매 정보 저장");

    private final String errorMessage;
}
