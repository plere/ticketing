package com.example.reservationservice.tempreservation.port.out;

import com.example.reservationservice.tempreservation.model.Place;

public interface GetPlacePort {
    Place getOrElseThrow(long id);
}
