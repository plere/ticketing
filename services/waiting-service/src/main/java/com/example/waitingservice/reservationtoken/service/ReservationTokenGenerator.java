package com.example.waitingservice.reservationtoken.service;

import com.example.waitingservice.reservationtoken.model.ReservationToken;
import com.example.waitingservice.waitingtoken.model.WaitingToken;

public interface ReservationTokenGenerator {
    ReservationToken generate(WaitingToken waitingToken);
}
