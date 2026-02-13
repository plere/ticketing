package com.example.concertservice.concert.adapter.in.web.response.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InternalConcertSeatErrorResponseCode {
    HOLD_SEATS_ERROR("좌석을 선택할 수 없습니다");

    private final String errorMessage;
}
