package com.example.reservationservice.reservation.application.port.out;

import com.example.reservationservice.reservation.domain.Concert;

public interface GetConcertPort {
    Concert getOrElseThrow(long id);
}
