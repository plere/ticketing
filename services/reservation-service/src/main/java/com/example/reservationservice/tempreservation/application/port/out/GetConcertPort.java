package com.example.reservationservice.tempreservation.port.out;

import com.example.reservationservice.tempreservation.model.Concert;

public interface GetConcertPort {
    Concert getOrElseThrow(long id);
}
