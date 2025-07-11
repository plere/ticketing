package com.example.waitingservice.reservationtoken.application.service;

import com.example.waitingservice.reservationtoken.model.ReservationToken;
import com.example.waitingservice.waitingtoken.model.WaitingToken;

public interface ReservationTokenGenerator {
    ReservationToken generate(WaitingToken waitingToken);
}
