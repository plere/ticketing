package com.example.reservationservice.tempreservation.port.out;

import com.example.reservationservice.tempreservation.model.TempReservation;

import java.util.Optional;

public interface GetTempReservationPort {
    Optional<TempReservation> find(TempReservation tempReservation);
}
