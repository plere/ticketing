package com.example.concertservice.concert.application.service.validation.seat;

import com.example.concertservice.concert.application.service.exception.seat.HoldConcertSeatValidationException;
import com.example.concertservice.concert.application.service.exception.seat.ReleaseHoldSeatsValidationException;
import com.example.concertservice.concert.domain.ConcertSeat;
import com.example.concertservice.concert.domain.ConcertSeatState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HoldConcertSeatValidation {
    public void validateSeatStateToHold(List<ConcertSeat> concertSeats) {
        if (concertSeats.isEmpty()) {
            throw new HoldConcertSeatValidationException();
        }

        if (
            concertSeats.stream().anyMatch(seat -> checkSeatState(seat, ConcertSeatState.EMPTY))
                || checkRoundId(concertSeats, concertSeats.get(0).roundId())
        ) {
            throw new HoldConcertSeatValidationException();
        }
    }

    public void validateReleaseHoldSeats(List<ConcertSeat> concertSeats) {
        if (concertSeats.isEmpty()) {
            throw new ReleaseHoldSeatsValidationException();
        }

        if (
            concertSeats.stream().anyMatch(seat -> checkSeatState(seat, ConcertSeatState.SELECT))
                || checkRoundId(concertSeats, concertSeats.get(0).roundId())
        ) {
            throw new ReleaseHoldSeatsValidationException();
        }
    }

    private boolean checkSeatState(ConcertSeat seat, ConcertSeatState state) {
        return seat.state() != state;
    }

    private boolean checkRoundId(List<ConcertSeat> seats, long roundId) {
        return seats.stream().anyMatch(seat -> seat.roundId() != roundId);
    }
}
