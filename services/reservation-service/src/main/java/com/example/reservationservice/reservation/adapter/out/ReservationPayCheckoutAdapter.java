package com.example.reservationservice.reservation.adapter.out;

import com.example.reservationservice.reservation.adapter.out.persistence.repository.jpa.ReservationJpaRepository;
import com.example.reservationservice.reservation.application.port.out.PaymentExecutorPort;
import com.example.reservationservice.reservation.application.port.out.ReservationPayCheckoutPort;
import com.example.reservationservice.reservation.domain.ReadyPaymentResult;
import com.example.reservationservice.reservation.domain.Reservation;
import com.example.reservationservice.reservation.domain.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReservationPayCheckoutAdapter implements ReservationPayCheckoutPort {
    private final ReservationJpaRepository reservationJpaRepository;
    private final PaymentExecutorPort paymentExecutorPort;

    @Override
    @Transactional
    public void ready(Reservation reservation) {
        reservationJpaRepository.findByIdAndStatusIs(reservation.id(), ReservationStatus.TEMP)
            .orElseThrow()
            .updatePayRequesting(reservation);
    }

    @Override
    @Transactional
    public void readyFail(Reservation reservation) {
        reservationJpaRepository.findByOrderIdAndStatusIs(reservation.orderId(), ReservationStatus.PAY_REQUESTING)
            .orElseThrow()
            .failReadyPayment();
    }

    @Override
    @Transactional
    public void readySuccess(Reservation reservation) {
        reservationJpaRepository.findByOrderIdAndStatusIs(reservation.orderId(), ReservationStatus.PAY_REQUESTING)
            .orElseThrow()
            .successReadyPayment();
    }

    @Override
    public ReadyPaymentResult pay(Reservation reservation) {
        return paymentExecutorPort.ready(reservation);
    }
}
