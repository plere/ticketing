package com.example.reservationservice.reservation.application.port.out;

import com.example.reservationservice.reservation.domain.TempReservation;

import java.util.Optional;

public interface GetTempReservationPort {
    Optional<TempReservation> find(TempReservation tempReservation);
}
