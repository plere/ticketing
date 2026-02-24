package com.example.reservationservice.tempreservation.service;

import com.example.reservationservice.tempreservation.model.TempReservation;
import com.example.reservationservice.tempreservation.port.out.GetTempReservationPort;
import com.example.reservationservice.tempreservation.port.out.HoldConcertSeatsPort;
import com.example.reservationservice.tempreservation.port.out.SaveTempReservationPort;
import com.example.reservationservice.tempreservation.service.exception.HoldSeatException;
import com.example.reservationservice.tempreservation.service.validation.TempReservationValidation;
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
        //Optional 무거운데 사용하면 안되나? 다시 확인/정리하기
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
