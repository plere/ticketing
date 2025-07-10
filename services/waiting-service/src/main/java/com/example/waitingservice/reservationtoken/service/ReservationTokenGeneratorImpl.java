package com.example.waitingservice.reservationtoken.service;

import com.example.waitingservice.reservationtoken.model.ReservationToken;
import com.example.waitingservice.waitingtoken.model.WaitingToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReservationTokenGeneratorImpl implements ReservationTokenGenerator {
    @Override
    public ReservationToken generate(WaitingToken waitingToken) {
        return ReservationToken.builder()
            .id(waitingToken.id())
            .token(UUID.randomUUID().toString())
            .build();
    }
}
