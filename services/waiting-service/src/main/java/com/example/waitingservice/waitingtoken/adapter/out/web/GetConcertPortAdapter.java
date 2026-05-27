package com.example.waitingservice.waitingtoken.adapter.out.web;

import com.example.waitingservice.waitingtoken.application.port.out.GetConcertPort;
import com.example.waitingservice.waitingtoken.model.ConcertState;
import org.springframework.stereotype.Component;

@Component
public class GetConcertPortAdapter implements GetConcertPort {
    @Override
    public ConcertState getConcertState(long concertId) {
        //todo
        return null;
    }

    @Override
    public boolean isIncludedRound(long concertId, long roundId) {
        //todo
        return false;
    }
}
