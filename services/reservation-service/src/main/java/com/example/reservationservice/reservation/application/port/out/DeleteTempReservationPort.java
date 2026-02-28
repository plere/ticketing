package com.example.reservationservice.reservation.application.port.out;

import com.example.reservationservice.reservation.domain.TempReservation;

import java.util.List;

public interface DeleteTempReservationPort {
    List<TempReservation> getOldTempReservationsBefore(int seconds);

    void deleteOldTempReservations(List<Long> oldTempReservationIds);
}
