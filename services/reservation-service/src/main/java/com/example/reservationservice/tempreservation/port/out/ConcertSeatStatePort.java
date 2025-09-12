package com.example.reservationservice.tempreservation.port.out;

import java.util.Set;

public interface ConcertSeatStatePort {
    boolean isEmpty(Set<Long> seatIds);
}
