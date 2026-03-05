package com.example.reservationservice.reservation.application.port.out;

import com.example.reservationservice.reservation.domain.ReadyPaymentResult;
import com.example.reservationservice.reservation.domain.Reservation;

public interface ReservationPayCheckoutPort {
    void ready(Reservation reservation);

    void readyFail(Reservation reservation);

    void readySuccess(Reservation reservation);

    ReadyPaymentResult pay(Reservation reservation);
}
