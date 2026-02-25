package com.example.reservationservice.tempreservation.application.port.out;

import java.util.Set;

public interface HoldConcertSeatsPort {
    void holdSeats(Set<Long> seatIds);

    void releaseSeats(Set<Long> concertSeatIds);
}
