package com.example.reservationservice.reservation.application.port.in;

import com.example.reservationservice.reservation.domain.ExecutePaymentCommand;
import com.example.reservationservice.reservation.domain.Reservation;

public interface ReservationExecutePaymentUseCase {
    Reservation execute(ExecutePaymentCommand command);
}
