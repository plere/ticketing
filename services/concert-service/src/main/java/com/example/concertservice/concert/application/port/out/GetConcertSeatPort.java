package com.example.concertservice.concert.application.port.out;

import com.example.concertservice.concert.domain.ConcertSeat;

import java.util.List;

public interface GetConcertSeatPort {
    List<ConcertSeat> getAllEmptyConcertSeatByRoundId(long roundId);
}
