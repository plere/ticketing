package com.example.waitingservice.waitingtoken.adapter.out.web;

import com.example.waitingservice.waitingtoken.adapter.out.web.feign.ConcertFeignClient;
import com.example.waitingservice.waitingtoken.application.port.out.GetConcertPort;
import com.example.waitingservice.waitingtoken.model.ConcertState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetConcertPortAdapter implements GetConcertPort {
    private final ConcertFeignClient concertFeignClient;

    @Override
    public ConcertState getConcertState(long concertId) {
        return concertFeignClient.getState(concertId).getBody();
    }

    @Override
    public boolean isIncludedRound(long concertId, long roundId) {
        return concertFeignClient.isIncluded(concertId, roundId).getBody();
    }
}
