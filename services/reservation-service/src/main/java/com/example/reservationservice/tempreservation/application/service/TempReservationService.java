package com.example.reservationservice.tempreservation.application.service;

import com.example.reservationservice.tempreservation.domain.TempReservation;
import com.example.reservationservice.tempreservation.application.port.out.GetTempReservationPort;
import com.example.reservationservice.tempreservation.application.port.out.HoldConcertSeatsPort;
import com.example.reservationservice.tempreservation.application.port.out.SaveTempReservationPort;
import com.example.reservationservice.tempreservation.application.service.exception.HoldSeatException;
import com.example.reservationservice.tempreservation.application.service.validation.TempReservationValidation;
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
    public void createAndHoldSeats(TempReservation tempReservation) {
        tempReservationValidation.validateTempReservation(tempReservation);

        holdConcertSeatsPort.holdSeats(tempReservation.seatIds());

        try {
            saveTempReservationPort.save(tempReservation);
        } catch (Exception e) {
            holdConcertSeatsPort.releaseSeats(tempReservation.seatIds());
            throw new HoldSeatException();
        }
    }
}
