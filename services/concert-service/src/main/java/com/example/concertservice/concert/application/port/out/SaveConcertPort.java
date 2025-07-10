package com.example.concertservice.concert.application.port.out;

import com.example.concertservice.concert.domain.Concert;

public interface SaveConcertPort {
    Concert save(Concert concert);
}
