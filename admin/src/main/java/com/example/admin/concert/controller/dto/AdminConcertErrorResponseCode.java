package com.example.admin.concert.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminConcertErrorResponseCode {
    NOT_EQUAL_SEATS_SIZE_TO_PLACE_ID_ERROR("선택된 장소의 좌석 개수와 일치하지 않습니다"),
    NOT_MATCH_SEATS_INFO_TO_PLACE_ID_ERROR("선택된 장소의 좌석 정보와 일치하지 않습니다"),
    CREATE_CONCERT_GRADE_INPUT_ERROR("좌석에 대한 등급 정보가 잘 못 입력되었습니다"),
    TICKETING_TIME_CANT_BEFORE_OPEN_TIME_ERROR("티케팅날은 오픈날 이후로 지정해야 합니다"),
    CONCERT_ROUND_TIME_ERROR("콘서트 공연일은 공연오픈일, 티케팅 날 이후여야 합니다"),
    NOT_FOUND_CONCERT_ERROR("콘서트 정보를 찾을 수 없습니다"),
    MODIFY_CONCERT_STATE_ERROR("현재 콘서트가 수정할 수 없는 상태입니다"),
    ;

    private final String errorMessage;
}
