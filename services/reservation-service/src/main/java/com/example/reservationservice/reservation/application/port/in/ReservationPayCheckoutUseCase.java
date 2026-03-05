package com.example.reservationservice.reservation.application.port.in;

import com.example.reservationservice.reservation.domain.Reservation;

public interface ReservationPayCheckoutUseCase {
    Reservation checkout(long tempReservationId, long userId);
}
