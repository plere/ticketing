package com.example.reservationservice.tempreservation.port.out;

import com.example.reservationservice.tempreservation.model.TempReservation;

public interface SaveTempReservationPort {
    TempReservation save(TempReservation tempReservation);
}
