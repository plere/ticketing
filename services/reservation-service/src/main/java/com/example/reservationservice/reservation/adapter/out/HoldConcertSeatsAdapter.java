package com.example.reservationservice.reservation.adapter.out;

import com.example.reservationservice.reservation.adapter.out.exception.LockHoldSeatException;
import com.example.reservationservice.reservation.adapter.out.persistence.HoldConcertCacheSeat;
import com.example.reservationservice.reservation.adapter.out.web.concert.ConcertSeatWebAdapter;
import com.example.reservationservice.reservation.application.port.out.HoldConcertSeatsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class HoldConcertSeatsAdapter implements HoldConcertSeatsPort {
    private final HoldConcertCacheSeat holdConcertCacheSeat;
    private final ConcertSeatWebAdapter concertSeatWebAdapter;

    @Override
    public void holdSeats(Set<Long> seatIds) {
        if (holdConcertCacheSeat.holdCacheSeats(seatIds)) {
            try {
                concertSeatWebAdapter.holdSeats(seatIds);
            } catch (Exception e) {
                holdConcertCacheSeat.releaseCacheSeats(seatIds);
                throw new LockHoldSeatException();
            }
        }
    }

    @Override
    public void releaseSeats(Set<Long> seatIds) {
        holdConcertCacheSeat.releaseCacheSeats(seatIds);
        concertSeatWebAdapter.releaseSeats(seatIds);
    }
}
