package com.example.reservationservice.tempreservation.application.service.validation;

import com.example.reservationservice.tempreservation.domain.Concert;
import com.example.reservationservice.tempreservation.domain.ConcertSeat;
import com.example.reservationservice.tempreservation.domain.TempReservation;
import com.example.reservationservice.tempreservation.application.port.out.GetConcertPort;
import com.example.reservationservice.tempreservation.application.service.exception.TempReservationValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TempReservationValidation {
    private final GetConcertPort getConcertPort;

    public void validateTempReservation(TempReservation tempReservation) {
        Concert concert = getConcertPort.getOrElseThrow(tempReservation.concertId());

        checkConcertInfo(tempReservation, concert);
    }

    private void checkConcertInfo(TempReservation tempReservation, Concert concert) {
        checkConcertSeat(tempReservation, concert);
        checkConcertRound(tempReservation, concert);
    }

    private void checkConcertRound(TempReservation tempReservation, Concert concert) {
        if (concert.rounds().stream().filter(round -> round.id() == tempReservation.roundId()).findAny()
            .isEmpty()) {
            throw new TempReservationValidException();
        }
    }


    private void checkConcertSeat(TempReservation tempReservation, Concert concert) {
        if (isNotMatchConcertSeat(tempReservation, concert)) {
            throw new TempReservationValidException();
        }
    }

    private boolean isNotMatchConcertSeat(TempReservation tempReservation, Concert concert) {
        Set<Long> concertSeatIds = concert.seats().stream().map(ConcertSeat::id).collect(Collectors.toSet());
        return tempReservation.seatIds().stream().anyMatch(seatId -> !concertSeatIds.contains(seatId));
    }
}

