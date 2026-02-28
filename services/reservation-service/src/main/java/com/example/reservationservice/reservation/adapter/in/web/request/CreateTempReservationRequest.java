package com.example.reservationservice.reservation.adapter.in.web.request;

import com.example.checkauth.UserToken;
import com.example.reservationservice.reservation.domain.TempReservation;

import java.util.Set;

public record CreateTempReservationRequest(
    long concertId,
    long roundId,
    Set<Long> seatIds
) {
    public TempReservation toModel(UserToken userToken) {
        return TempReservation.builder()
            .userId(userToken.getId())
            .concertId(concertId)
            .roundId(roundId)
            .seatIds(Set.copyOf(seatIds))
            .build();
    }
}
