package com.example.reservationservice.reservation.application.service;

import com.example.reservationservice.reservation.application.port.in.ReservationExecutePaymentUseCase;
import com.example.reservationservice.reservation.application.port.out.ReservationExecutePaymentPort;
import com.example.reservationservice.reservation.application.service.exception.FailExecutePaymentException;
import com.example.reservationservice.reservation.domain.ExecutePaymentCommand;
import com.example.reservationservice.reservation.domain.ExecutePaymentResult;
import com.example.reservationservice.reservation.domain.Reservation;
import com.example.reservationservice.reservation.domain.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationExecutePaymentService implements ReservationExecutePaymentUseCase {
    private final ReservationExecutePaymentPort reservationExecutePaymentPort;

    @Override
    public Reservation execute(ExecutePaymentCommand command) {
        Reservation reservation = createReservation(command);
        reservationExecutePaymentPort.ready(reservation);

        ExecutePaymentResult result = reservationExecutePaymentPort.execute(reservation);

        switch (result.status()) {
            case SUCCESS -> reservationExecutePaymentPort.success(reservation);
            case FAIL -> {
                reservationExecutePaymentPort.fail(reservation);
                throw new FailExecutePaymentException();
            }
        }

        return reservation;
    }

    private Reservation createReservation(ExecutePaymentCommand command) {
        return Reservation.builder()
            .id(command.id())
            .userId(command.userId())
            .paymentKey(command.paymentKey())
            .orderId(command.orderId())
            .amount(command.amount())
            .status(ReservationStatus.PAY_EXECUTING)
            .build();
    }
}
