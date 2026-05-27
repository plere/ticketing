package com.example.waitingservice.waitingtoken.application.validation;

import com.example.waitingservice.waitingtoken.application.exception.ConcertReservationStateException;
import com.example.waitingservice.waitingtoken.application.port.out.GetConcertPort;
import com.example.waitingservice.waitingtoken.model.ConcertState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcertValidation {
    private final GetConcertPort getConcertPort;

    public void checkReservationStateConcert(long concertId, long roundId) {
        if (getConcertPort.getConcertState(concertId) != ConcertState.OPEN ||
            !getConcertPort.isIncludedRound(concertId, roundId)) {
            throw new ConcertReservationStateException(concertId, roundId);
        }
    }
}
