package com.example.concertservice.concert.domain;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;


@Builder
public record Concert(
    Long id,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String name,
    String detailInfo,
    int runningTime,
    ConcertState state,
    LocalDateTime ticketingStartTime,
    LocalDateTime openTime,
    Long placeId,
    String placeName,
    List<ConcertSeat> seats,
    List<ConcertRound> rounds,
    List<ConcertSeatGrade> seatGrades
) {
}
