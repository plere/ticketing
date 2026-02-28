package com.example.reservationservice.reservation.application.port.out;

import java.util.Set;

public interface HoldConcertSeatsPort {
    void holdSeats(Set<Long> seatIds);

    void releaseSeats(Set<Long> concertSeatIds);
}
