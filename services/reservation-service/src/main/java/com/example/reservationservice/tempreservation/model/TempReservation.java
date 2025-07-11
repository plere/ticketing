package com.example.reservationservice.tempreservation.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record TempReservation(
    long userId,
    String address,
    long concertId,
    String concertName,
    LocalDateTime concertDateTime,
    String concertPlaceAddress,
    List<TempReservationSeat> seats
) {
    @Builder
    public record TempReservationSeat(
        long seatId,
        String seatName
    ) {
    }
}
