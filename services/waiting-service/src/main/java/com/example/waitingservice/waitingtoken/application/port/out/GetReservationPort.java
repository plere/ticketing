package com.example.waitingservice.waitingtoken.application.port.out;

public interface GetReservationPort {
    int availableProcessingCount(long concertId);
}
