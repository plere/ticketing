package com.example.reservationservice.reservation.adapter.out.persistence;

import java.util.Set;

public interface HoldConcertCacheSeat {
    boolean holdCacheSeats(Set<Long> seatIds);

    void releaseCacheSeats(Set<Long> seatIds);
}
