package com.example.concertservice.concert.adapter.out.persistence;

import com.example.concertservice.concert.adapter.out.persistence.repository.ConcertSeatRepository;
import com.example.concertservice.concert.application.port.out.GetConcertSeatPort;
import com.example.concertservice.concert.application.port.out.seat.HoldConcertSeatPort;
import com.example.concertservice.concert.domain.ConcertSeat;
import com.example.concertservice.concert.domain.ConcertSeatState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ConcertSeatPersistenceAdapter implements GetConcertSeatPort, HoldConcertSeatPort {
    private final ConcertSeatRepository concertSeatRepository;

    @Override
    public List<ConcertSeat> getAllConcertSeats(Set<Long> seatIds) {
        return concertSeatRepository.getAll(seatIds);
    }

    @Override
    public List<ConcertSeat> getAllConcertSeatByRoundId(long roundId) {
        return concertSeatRepository.getAllByRoundId(roundId);
    }

    @Override
    public List<ConcertSeat> getAllEmptyConcertSeatByRoundId(long roundId) {
        return concertSeatRepository.getAllByRoundIdAndState(roundId, ConcertSeatState.EMPTY);
    }

    @Override
    public List<ConcertSeat> findAll(Set<Long> seatIds) {
        return concertSeatRepository.getAllWithLock(seatIds);
    }

    @Override
    public void holdSeats(Set<Long> seatIds) {
        concertSeatRepository.changeState(seatIds, ConcertSeatState.SELECT);
    }
}
