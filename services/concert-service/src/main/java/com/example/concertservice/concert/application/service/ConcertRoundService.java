package com.example.concertservice.concert.application.service;

import com.example.concertservice.concert.application.port.in.usecase.round.ConcertRoundGetUseCase;
import com.example.concertservice.concert.application.port.out.GetConcertPort;
import com.example.concertservice.concert.application.port.out.GetConcertSeatPort;
import com.example.concertservice.concert.domain.Concert;
import com.example.concertservice.concert.domain.ConcertSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConcertRoundService implements ConcertRoundGetUseCase {
    private final GetConcertPort getConcertPort;
    private final GetConcertSeatPort getConcertSeatPort;

    @Override
    public List<ConcertSeat> getAllConcertSeatByRoundId(long roundId) {
        return getConcertSeatPort.getAllConcertSeatByRoundId(roundId);
    }

    @Override
    public List<ConcertSeat> getAllEmptyConcertSeatByRoundId(long roundId) {
        return getConcertSeatPort.getAllEmptyConcertSeatByRoundId(roundId);
    }

    @Override
    public boolean isIncluded(long concertId, long roundId) {
        Concert concert = getConcertPort.getConcertOrElseThrow(concertId);
        return concert.rounds().stream().anyMatch(round -> round.id() == roundId);
    }
}
