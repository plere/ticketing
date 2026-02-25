package com.example.reservationservice.tempreservation.application.port.out;

import com.example.reservationservice.tempreservation.domain.Place;

public interface GetPlacePort {
    Place getOrElseThrow(long id);
}
