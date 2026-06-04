package com.example.waitingservice.reservationtoken.application.port.out;

import com.example.waitingservice.reservationtoken.model.ReservationToken;

public interface CheckReservationTokenPort {
    boolean isValid(ReservationToken token);
}
