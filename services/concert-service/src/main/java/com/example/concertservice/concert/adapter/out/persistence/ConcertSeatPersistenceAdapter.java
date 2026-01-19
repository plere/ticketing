package com.example.concertservice.concert.adapter.out.persistence;

import com.example.concertservice.concert.adapter.out.persistence.repository.ConcertSeatRepository;
import com.example.concertservice.concert.application.port.out.GetConcertSeatPort;
import com.example.concertservice.concert.domain.ConcertSeat;
import com.example.concertservice.concert.domain.ConcertSeatState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConcertSeatPersistenceAdapter implements GetConcertSeatPort {
    private final ConcertSeatRepository concertSeatRepository;

    @Override
    public List<ConcertSeat> getAllEmptyConcertSeatByRoundId(long roundId) {
        return concertSeatRepository.getAllByRoundIdAndState(roundId, ConcertSeatState.EMPTY);
    }
}
