package com.example.reservationservice.reservation.application.port.out;

import com.example.reservationservice.reservation.domain.TempReservation;

public interface SaveTempReservationPort {
    void save(TempReservation tempReservation);
}
