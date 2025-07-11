package com.example.reservationservice.tempreservation.service;

import com.example.reservationservice.tempreservation.model.TempReservation;
import com.example.reservationservice.tempreservation.port.out.GetTempReservationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TempReservationService {
    private final GetTempReservationPort tempReservationPort;

    public Boolean isExist(TempReservation tempReservation) {
        return tempReservationPort.get(tempReservation) != null;
    }
}
