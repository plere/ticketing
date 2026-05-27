package com.example.waitingservice.waitingtoken.application.service;

import com.example.waitingservice.waitingtoken.model.WaitingToken;

public interface WaitingTokenGenerator {
    String getId(long concertId, long roundId);

    WaitingToken generate(long concertId, long roundId);
}
