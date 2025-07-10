package com.example.concertservice.concert.application.port.in.usecase;

import com.example.concertservice.concert.domain.Concert;

public interface ConcertCreateUseCase {
    long create(Concert concert);
}
