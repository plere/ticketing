package com.example.reservationservice.reservation.adapter.out;

import com.example.reservationservice.reservation.adapter.out.persistence.repository.jpa.ReservationJpaRepository;
import com.example.reservationservice.reservation.application.port.out.PaymentExecutorPort;
import com.example.reservationservice.reservation.application.port.out.ReservationExecutePaymentPort;
import com.example.reservationservice.reservation.domain.ExecutePaymentResult;
import com.example.reservationservice.reservation.domain.Reservation;
import com.example.reservationservice.reservation.domain.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReservationExecutePaymentAdapter implements ReservationExecutePaymentPort {
    private final ReservationJpaRepository reservationJpaRepository;
    private final PaymentExecutorPort paymentExecutorPort;

    @Override
    @Transactional
    public void ready(Reservation reservation) {
        reservationJpaRepository.findByIdAndStatusIs(reservation.id(), ReservationStatus.PAY_REQUESTED)
            .orElseThrow()
            .updatePayExecuting(reservation);
    }

    @Override
    public ExecutePaymentResult execute(Reservation reservation) {
        return paymentExecutorPort.execute(reservation);
    }

    @Override
    @Transactional
    public void fail(Reservation reservation) {
        reservationJpaRepository.findByOrderIdAndStatusIs(reservation.orderId(), ReservationStatus.PAY_EXECUTING)
            .orElseThrow()
            .failExecutePayment();

    }

    @Override
    @Transactional
    public void success(Reservation reservation) {
        reservationJpaRepository.findByOrderIdAndStatusIs(reservation.orderId(), ReservationStatus.PAY_EXECUTING)
            .orElseThrow()
            .successExecutePayment();
    }
}
