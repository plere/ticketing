package com.example.waitingservice.waitingtoken.application.service;

import com.example.waitingservice.waitingtoken.model.WaitingToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WaitingTokenGeneratorImpl implements WaitingTokenGenerator {
    @Override
    public String getId(long concertId, long roundId) {
        return String.format("%s-%s", concertId, roundId);
    }

    @Override
    public WaitingToken generate(long concertId, long roundId) {
        return WaitingToken.builder()
            .id(getId(concertId, roundId))
            .token(UUID.randomUUID().toString())
            .build();
    }
}
