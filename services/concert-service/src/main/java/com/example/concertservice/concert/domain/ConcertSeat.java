package com.example.concertservice.concert.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertSeat(
    Long id,
    LocalDateTime createdAt,
    int floor,
    Integer rowOrder,
    String rowName,
    int columnNum,
    ConcertSeatState state,
    ConcertSeatGrade grade,
    Long roundId
) {
    public String getGradeName() {
        return grade.name();
    }
}
