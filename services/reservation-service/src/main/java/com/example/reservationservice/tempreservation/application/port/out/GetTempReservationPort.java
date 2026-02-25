package com.example.reservationservice.tempreservation.application.port.out;

import com.example.reservationservice.tempreservation.domain.TempReservation;

import java.util.Optional;

public interface GetTempReservationPort {
    Optional<TempReservation> find(TempReservation tempReservation);
}
