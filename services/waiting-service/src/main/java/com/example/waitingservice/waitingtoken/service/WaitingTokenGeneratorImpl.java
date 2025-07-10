package com.example.waitingservice.waitingtoken.service;

import com.example.waitingservice.waitingtoken.model.WaitingToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WaitingTokenGeneratorImpl implements WaitingTokenGenerator {
    @Override
    public WaitingToken generate(long id) {
        return WaitingToken.builder()
            .id(id)
            .token(UUID.randomUUID().toString())
            .build();
    }
}
