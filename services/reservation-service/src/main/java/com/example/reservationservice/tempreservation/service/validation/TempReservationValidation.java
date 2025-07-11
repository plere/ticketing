package com.example.reservationservice.tempreservation.service.validation;

import com.example.httpresponse.exception.BadRequestException;
import com.example.reservationservice.tempreservation.model.Concert;
import com.example.reservationservice.tempreservation.model.ConcertSeat;
import com.example.reservationservice.tempreservation.model.Place;
import com.example.reservationservice.tempreservation.model.TempReservation;
import com.example.reservationservice.tempreservation.port.out.GetConcertPort;
import com.example.reservationservice.tempreservation.port.out.GetPlacePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.reservationservice.tempreservation.controller.TempReservationErrorCode.RESERVATION_BAD_REQUEST_ERROR;

@Component
@RequiredArgsConstructor
public class TempReservationValidation {
    private final GetConcertPort getConcertPort;
    private final GetPlacePort getPlacePort;

    public TempReservation toValidateModel(TempReservation tempReservation) {
        Concert concert = getConcertPort.getOrElseThrow(tempReservation.concertId());
        Place place = getPlacePort.getOrElseThrow(concert.placeId());

        return checkPlaceInfo(checkConcertInfo(tempReservation, concert), place);
    }

    private TempReservation checkConcertInfo(TempReservation tempReservation, Concert concert) {
        checkConcertName(tempReservation, concert);
        checkConcertDateTime(tempReservation, concert);
        checkConcertSeat(tempReservation, concert);

        return tempReservation.setConcertInfo(concert);
    }

    private void checkConcertName(TempReservation tempReservation, Concert concert) {
        if (!tempReservation.concertName().equals(concert.name())) {
            throw new BadRequestException(RESERVATION_BAD_REQUEST_ERROR, RESERVATION_BAD_REQUEST_ERROR.getErrorMessage());
        }
    }

    private void checkConcertDateTime(TempReservation tempReservation, Concert concert) {
        if (!isMatchConcertRoundTime(tempReservation, concert)) {
            throw new BadRequestException(RESERVATION_BAD_REQUEST_ERROR, RESERVATION_BAD_REQUEST_ERROR.getErrorMessage());
        }
    }

    private void checkConcertSeat(TempReservation tempReservation, Concert concert) {
        if (isNotMatchConcertSeat(tempReservation, concert)) {
            throw new BadRequestException(RESERVATION_BAD_REQUEST_ERROR, RESERVATION_BAD_REQUEST_ERROR.getErrorMessage());
        }
    }

    private boolean isMatchConcertRoundTime(TempReservation tempReservation, Concert concert) {
        return concert.rounds()
            .stream()
            .anyMatch(round -> round.startDateTime().isEqual(tempReservation.concertDateTime())
                && round.id().equals(tempReservation.roundId()
            ));
    }

    private boolean isNotMatchConcertSeat(TempReservation tempReservation, Concert concert) {
        Set<Long> concertSeatIds = concert.seats().stream().map(ConcertSeat::id).collect(Collectors.toSet());
        return tempReservation.seats().stream().anyMatch(seat -> !concertSeatIds.contains(seat.seatId()));
    }

    private TempReservation checkPlaceInfo(TempReservation tempReservation, Place place) {
        if (!tempReservation.concertPlaceName().equals(place.name())) {
            throw new BadRequestException(RESERVATION_BAD_REQUEST_ERROR, RESERVATION_BAD_REQUEST_ERROR.getErrorMessage());
        }

        if (!tempReservation.concertPlaceAddress().equals(place.address())) {
            throw new BadRequestException(RESERVATION_BAD_REQUEST_ERROR, RESERVATION_BAD_REQUEST_ERROR.getErrorMessage());
        }

        return tempReservation;
    }
}

