package com.example.waitingservice.waitingtoken.adapter.out.web.reservation;

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
