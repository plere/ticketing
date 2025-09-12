package com.example.reservationservice.tempreservation.port.out;

import com.example.reservationservice.tempreservation.model.Concert;

import java.util.Set;

public interface GetConcertPort {
    Concert getOrElseThrow(long id);

    Boolean isEmptySeats(long concertId, Set<Long> concertSeatIds);
}
