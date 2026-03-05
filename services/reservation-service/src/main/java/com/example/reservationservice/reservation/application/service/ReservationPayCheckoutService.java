package com.example.reservationservice.reservation.application.service;

import com.example.reservationservice.common.IdempotencyCreator;
import com.example.reservationservice.reservation.application.port.in.ReservationPayCheckoutUseCase;
import com.example.reservationservice.reservation.application.port.out.GetConcertPort;
import com.example.reservationservice.reservation.application.port.out.GetTempReservationPort;
import com.example.reservationservice.reservation.application.port.out.ReservationPayCheckoutPort;
import com.example.reservationservice.reservation.application.service.exception.PayCheckoutCallException;
import com.example.reservationservice.reservation.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class ReservationPayCheckoutService implements ReservationPayCheckoutUseCase {
    private final GetTempReservationPort getTempReservationPort;
    private final ReservationPayCheckoutPort reservationPayCheckoutPort;
    private final GetConcertPort getConcertPort;

    @Override
    public Reservation checkout(long tempReservationId, long userId) {
        Reservation reservation = createReservation(tempReservationId, userId);
        reservationPayCheckoutPort.ready(reservation);

        ReadyPaymentResult paymentResult = reservationPayCheckoutPort.pay(reservation);

        switch (paymentResult.status()) {
            case SUCCESS:
                reservationPayCheckoutPort.readySuccess(reservation);
            case FAIL:
                reservationPayCheckoutPort.readyFail(reservation);
                throw new PayCheckoutCallException();
        }

        return reservation;
    }

    private Reservation createReservation(long tempReservationId, long userId) {
        TempReservation tempReservation = getTempReservationPort.get(tempReservationId, userId);
        Concert concert = getConcertPort.getOrElseThrow(tempReservation.concertId());
        return Reservation.builder()
            .id(tempReservation.id())
            .userId(tempReservation.userId())
            .orderId(IdempotencyCreator.create())
            .concertId(tempReservation.concertId())
            .concertName(concert.name())
            .roundId(tempReservation.roundId())
            .amount(concert.seats().stream().mapToLong(ConcertSeat::getPrice).sum())
            .seatIds(Set.copyOf(tempReservation.seatIds()))
            .build();
    }
}
