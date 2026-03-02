package com.example.reservationservice.reservation.adapter.out.web.concert;

import java.util.Set;

public interface ConcertSeatWebAdapter {
    void holdSeats(Set<Long> seatIds);

    void releaseSeats(Set<Long> seatIds);
}
