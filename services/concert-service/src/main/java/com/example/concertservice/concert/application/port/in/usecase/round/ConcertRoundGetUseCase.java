package com.example.concertservice.concert.application.port.in.usecase.round;

import com.example.concertservice.concert.domain.ConcertSeat;

import java.util.List;


public interface ConcertRoundGetUseCase {
    List<ConcertSeat> getAllConcertSeatByRoundId(long roundId);

    List<ConcertSeat> getAllEmptyConcertSeatByRoundId(long roundId);
}
