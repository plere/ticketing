package com.example.reservationservice.reservation.application.port.out;

import com.example.reservationservice.reservation.domain.ReadyPaymentResult;
import com.example.reservationservice.reservation.domain.Reservation;

public interface PaymentExecutorPort {
    ReadyPaymentResult ready(Reservation reservation);
}
