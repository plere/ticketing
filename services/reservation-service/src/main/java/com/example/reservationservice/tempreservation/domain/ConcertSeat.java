package com.example.reservationservice.tempreservation.domain;

import lombok.Builder;

@Builder
public record ConcertSeat(
    Long id,
    int floor,
    Integer rowOrder,
    int columnNum,
    ConcertSeatGrade grade
) {
}
