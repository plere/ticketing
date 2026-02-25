package com.example.reservationservice.tempreservation.application.port.out;

import com.example.reservationservice.tempreservation.domain.Concert;

public interface GetConcertPort {
    Concert getOrElseThrow(long id);
}
