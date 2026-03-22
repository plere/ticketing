package com.example.reservationservice.reservation.application.port.out;

import com.example.reservationservice.reservation.domain.ExecutePaymentResult;
import com.example.reservationservice.reservation.domain.Reservation;

public interface ReservationExecutePaymentPort {
    void ready(Reservation reservation);

    ExecutePaymentResult execute(Reservation reservation);

    void fail(Reservation reservation);

    void success(Reservation reservation);
}
