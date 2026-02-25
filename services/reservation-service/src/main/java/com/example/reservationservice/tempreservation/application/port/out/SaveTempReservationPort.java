package com.example.reservationservice.tempreservation.application.port.out;

import com.example.reservationservice.tempreservation.domain.TempReservation;

public interface SaveTempReservationPort {
    void save(TempReservation tempReservation);
}
