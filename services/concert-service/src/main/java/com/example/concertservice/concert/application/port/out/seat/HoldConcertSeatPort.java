package com.example.concertservice.concert.application.port.out.seat;

import com.example.concertservice.concert.domain.ConcertSeat;

import java.util.List;
import java.util.Set;

public interface HoldConcertSeatPort {
    List<ConcertSeat> findAll(Set<Long> seatIds);

    void holdSeats(Set<Long> seatIds);
}
