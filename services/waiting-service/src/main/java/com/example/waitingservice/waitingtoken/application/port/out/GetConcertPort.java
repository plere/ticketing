package com.example.waitingservice.waitingtoken.application.port.out;

import com.example.waitingservice.waitingtoken.model.ConcertState;

public interface GetConcertPort {
    ConcertState getConcertState(long concertId);

    boolean isIncludedRound(long concertId, long roundId);
}
