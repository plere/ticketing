package com.example.concertservice.concert.adapter.in.web.response.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InternalConcertSeatErrorResponseCode {
    HOLD_SEATS_ERROR("좌석을 선택할 수 없습니다"),
    HOLD_SEAT_VALID_ERROR("요청이 잘 못 됏습니다"),
    RELEASE_HOLD_SEAT_VALID_ERROR("요청이 잘 못 됏습니다");

    private final String errorMessage;
}
