package com.example.concertservice.concert.application.port.in.usecase.seat;

import java.util.Set;

public interface HoldConcertSeatUseCase {
    void holdSeats(Set<Long> seatIds);
}
