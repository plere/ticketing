package com.example.reservationservice.tempreservation.model;

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
