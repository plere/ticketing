package com.example.concertservice.concert.adapter.out.persistence.repository;

import com.example.concertservice.concert.domain.ConcertSeat;
import com.example.concertservice.concert.domain.ConcertSeatState;

import java.util.List;
import java.util.Set;

public interface ConcertSeatRepository {
    List<ConcertSeat> getAll(Set<Long> seatIds);

    List<ConcertSeat> getAllWithLock(Set<Long> seatIds);

    List<ConcertSeat> getAllByRoundId(long roundId);

    List<ConcertSeat> getAllByRoundIdAndState(long roundId, ConcertSeatState state);

    void changeState(Set<Long> seatIds, ConcertSeatState state);
}
