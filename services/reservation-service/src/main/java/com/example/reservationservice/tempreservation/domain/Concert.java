package com.example.reservationservice.tempreservation.domain;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record Concert(
    Long id,
    String name,
    String detailInfo,
    int runningTime,
    LocalDateTime ticketingStartTime,
    LocalDateTime openTime,
    Long placeId,
    List<ConcertSeat> seats,
    List<ConcertRound> rounds,
    List<ConcertSeatGrade> seatGrades
) {
}
