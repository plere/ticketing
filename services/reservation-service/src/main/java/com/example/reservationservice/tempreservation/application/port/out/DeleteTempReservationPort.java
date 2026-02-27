package com.example.reservationservice.tempreservation.application.port.out;

import com.example.reservationservice.tempreservation.domain.TempReservation;

import java.util.List;

public interface DeleteTempReservationPort {
    List<TempReservation> getOldTempReservationsBefore(int seconds);

    void deleteOldTempReservations(List<Long> oldTempReservationIds);
}
