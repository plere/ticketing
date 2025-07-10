package com.example.concertservice.concert.application.service.validation;

import com.example.concertservice.concert.application.port.out.GetPlacePort;
import com.example.concertservice.concert.domain.Concert;
import com.example.concertservice.concert.domain.PlaceSeat;
import com.example.httpresponse.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.concertservice.concert.adapter.in.web.response.AdminConcertErrorResponseCode.*;
import static java.util.stream.Collectors.groupingBy;

@Component
@RequiredArgsConstructor
public class ConcertValidation {
    private final GetPlacePort getPlacePort;

    public void checkRounds(Concert concert) {
        if (concert.openTime() != null || concert.ticketingStartTime() != null) {
            checkRoundTime(concert);
        }
    }


    public void checkTicketingTime(Concert concert) {
        if (!isTicketingTimeAfterOpenTime(concert)) {
            throw new BadRequestException(TICKETING_TIME_CANT_BEFORE_OPEN_TIME_ERROR, TICKETING_TIME_CANT_BEFORE_OPEN_TIME_ERROR.getErrorMessage());
        }
    }

    public void checkAllPlaceSeat(Concert concert) {
        if (concert.openTime() != null || !concert.seats().isEmpty()) {
            checkPlaceSeat(concert);
        }
    }

    public void checkSeatGrade(Concert concert) {
        Set<String> gradeSet = new HashSet<>();
        Set<String> seatGradeSet = new HashSet<>();

        concert.seatGrades().forEach(grade -> gradeSet.add(grade.name()));
        concert.seats().forEach(seat -> seatGradeSet.add(seat.getGradeName()));

        if (gradeSet.size() != seatGradeSet.size()) {
            throw new BadRequestException(CREATE_CONCERT_GRADE_INPUT_ERROR, CREATE_CONCERT_GRADE_INPUT_ERROR.getErrorMessage());
        }

        gradeSet.forEach(grade -> {
            if (!seatGradeSet.contains(grade)) {
                throw new BadRequestException(CREATE_CONCERT_GRADE_INPUT_ERROR, CREATE_CONCERT_GRADE_INPUT_ERROR.getErrorMessage());
            }
        });
    }

    private void checkRoundTime(Concert concert) {

        concert.rounds().forEach(round -> {
                if ((concert.openTime() != null && round.startDateTime().isBefore(concert.openTime()))
                    || (concert.ticketingStartTime() != null && round.startDateTime().isBefore(concert.ticketingStartTime()))) {
                    throw new BadRequestException(CONCERT_ROUND_TIME_ERROR, CONCERT_ROUND_TIME_ERROR.getErrorMessage());
                }
            }
        );
    }

    private void checkPlaceSeat(Concert concert) {
        List<PlaceSeat> allSeats = getPlacePort.getAllSeatsByPlaceId(concert.placeId());

        if (allSeats.isEmpty()) {
            throw new BadRequestException(PLACE_OR_SEATS_ERROR, PLACE_OR_SEATS_ERROR.getErrorMessage());
        }

        if (registeredSeatCount(allSeats) != concert.seats().size()) {
            throw new BadRequestException(NOT_EQUAL_SEATS_SIZE_TO_PLACE_ID_ERROR, NOT_EQUAL_SEATS_SIZE_TO_PLACE_ID_ERROR.getErrorMessage());
        }

        isValidSeat(concert, allSeats);
    }

    private int registeredSeatCount(List<PlaceSeat> allSeats) {
        return allSeats.stream().mapToInt(PlaceSeat::getRowCount).sum();
    }

    private void isValidSeat(Concert concert, List<PlaceSeat> allSeats) {
        Map<Long, List<PlaceSeat>> seatsMap = allSeats.stream().collect(groupingBy(PlaceSeat::getId));

        if (concert.seats().size() != registeredSeatCount(allSeats)) {
            throw new BadRequestException(NOT_MATCH_SEATS_INFO_TO_PLACE_ID_ERROR, NOT_MATCH_SEATS_INFO_TO_PLACE_ID_ERROR.getErrorMessage());
        }

        concert.seats().forEach(seat -> {
            List<PlaceSeat> foundPlaceSeat = seatsMap.get(seat.id());
            if (foundPlaceSeat == null || foundPlaceSeat.get(0).getRowCount() < seat.columnNum()) {
                throw new BadRequestException(NOT_MATCH_SEATS_INFO_TO_PLACE_ID_ERROR, NOT_MATCH_SEATS_INFO_TO_PLACE_ID_ERROR.getErrorMessage());
            }
        });
    }

    private boolean isTicketingTimeAfterOpenTime(Concert concert) {
        if (concert.ticketingStartTime() != null
            && concert.openTime() != null
            && concert.ticketingStartTime().isBefore(concert.openTime())) {
            return false;
        }
        return true;
    }
}
