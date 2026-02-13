package com.example.concertservice.concert.application.port.out;

import com.example.concertservice.concert.domain.ConcertSeat;

import java.util.List;
import java.util.Set;

public interface GetConcertSeatPort {
    List<ConcertSeat> getAllConcertSeats(Set<Long> seatIds);

    List<ConcertSeat> getAllConcertSeatByRoundId(long roundId);

    List<ConcertSeat> getAllEmptyConcertSeatByRoundId(long roundId);
}
