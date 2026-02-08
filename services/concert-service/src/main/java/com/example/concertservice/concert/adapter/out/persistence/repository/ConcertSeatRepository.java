package com.example.concertservice.concert.adapter.out.persistence.repository;

import com.example.concertservice.concert.domain.ConcertSeat;
import com.example.concertservice.concert.domain.ConcertSeatState;

import java.util.List;

public interface ConcertSeatRepository {
    List<ConcertSeat> getAllByRoundId(long roundId);

    List<ConcertSeat> getAllByRoundIdAndState(long roundId, ConcertSeatState state);
}
