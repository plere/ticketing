package com.example.reservationservice.reservation.adapter.out.web;

import java.util.Set;

public interface ConcertSeatWebAdapter {
    void holdSeats(Set<Long> seatIds);

    void releaseSeats(Set<Long> seatIds);
}
