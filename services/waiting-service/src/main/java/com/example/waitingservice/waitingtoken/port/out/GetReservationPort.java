package com.example.waitingservice.waitingtoken.port.out;

public interface GetReservationPort {
    int availableProcessingCount(long concertId);
}
