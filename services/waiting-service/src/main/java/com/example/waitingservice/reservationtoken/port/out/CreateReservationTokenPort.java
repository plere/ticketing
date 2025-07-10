package com.example.waitingservice.reservationtoken.port.out;

import com.example.waitingservice.reservationtoken.model.ReservationToken;

public interface CreateReservationTokenPort {
    ReservationToken create(ReservationToken reservationToken);
}
