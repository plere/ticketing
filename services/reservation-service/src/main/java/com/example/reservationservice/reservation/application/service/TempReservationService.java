package com.example.reservationservice.reservation.application.service;

import com.example.reservationservice.reservation.application.port.out.GetTempReservationPort;
import com.example.reservationservice.reservation.application.port.out.HoldConcertSeatsPort;
import com.example.reservationservice.reservation.application.port.out.SaveTempReservationPort;
import com.example.reservationservice.reservation.application.service.exception.HoldSeatException;
import com.example.reservationservice.reservation.application.service.validation.TempReservationValidation;
import com.example.reservationservice.reservation.domain.TempReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TempReservationService {
    private final GetTempReservationPort getTempReservationPort;
    private final SaveTempReservationPort saveTempReservationPort;
    private final TempReservationValidation tempReservationValidation;
    private final HoldConcertSeatsPort holdConcertSeatsPort;

    public TempReservation get(TempReservation tempReservation) {
        return getTempReservationPort.find(tempReservation)
            .orElse(null);
    }

    @Transactional
    public TempReservation createAndHoldSeats(TempReservation tempReservation) {
        tempReservationValidation.validateTempReservation(tempReservation);

        holdConcertSeatsPort.holdSeats(tempReservation.seatIds());

        try {
            saveTempReservationPort.save(tempReservation);
            return getTempReservationPort.find(tempReservation).orElseThrow();
        } catch (Exception e) {
            holdConcertSeatsPort.releaseSeats(tempReservation.seatIds());
            throw new HoldSeatException();
        }
    }
}
