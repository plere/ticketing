package com.example.reservationservice.reservation.application.port.out;

import com.example.reservationservice.reservation.domain.Place;

public interface GetPlacePort {
    Place getOrElseThrow(long id);
}
