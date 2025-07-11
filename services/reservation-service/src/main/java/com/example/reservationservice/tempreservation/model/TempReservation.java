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
    long roundId,
    LocalDateTime concertDateTime,
    String concertPlaceName,
    String concertPlaceAddress,
    List<TempReservationSeat> seats
) {
    @Builder
    public record TempReservationSeat(
        long seatId,
        String seatName
    ) {
    }

    public TempReservation setConcertInfo(Concert concert) {
        return TempReservation.builder()
            .userId(userId)
            .address(address)
            .concertId(concert.id())
            .concertName(concert.name())
            .roundId(roundId)
            .concertDateTime(concertDateTime)
            .concertPlaceName(concertPlaceName)
            .concertPlaceAddress(concertPlaceAddress)
            .seats(List.copyOf(seats))
            .build();
    }
}
