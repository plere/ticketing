package com.example.waitingservice.waitingtoken.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WaitingTokenResponseCode {
    CREATED_WAITING_TOKEN("대기 토큰 생성완료"),
    GET_MY_WAITING_POSITION("현재 내 대기번호 조회");

    private final String errorMessage;
}
