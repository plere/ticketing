package com.example.waitingservice.reservationtoken.adapter.out.web;

import com.example.waitingservice.waitingtoken.port.out.GetReservationPort;
import org.springframework.stereotype.Component;

@Component
public class ReservationWebAdapter implements GetReservationPort {
    @Override
    public int availableProcessingCount(long concertId) {
        //Todo
        return 1;
    }
}
