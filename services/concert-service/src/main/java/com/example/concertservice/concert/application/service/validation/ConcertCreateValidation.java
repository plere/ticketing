package com.example.concertservice.concert.application.service.validation;

import com.example.concertservice.concert.domain.*;
import com.example.concertservice.place.application.port.out.GetAllPlaceSeatsPort;
import com.example.concertservice.place.application.service.PlaceService;
import com.example.concertservice.place.domain.Place;
import com.example.concertservice.place.domain.PlaceSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Component
@RequiredArgsConstructor
public class ConcertCreateValidation {
    private final ConcertValidation concertValidation;
    private final GetAllPlaceSeatsPort getAllPlaceSeatsPort;
    private final PlaceService placeService;

    public Concert toValidateConcert(Concert concert) {
        validate(concert);
        return toModel(concert);
    }

    private void validate(Concert concert) {
        concertValidation.checkRounds(concert);
        concertValidation.checkTicketingTime(concert);
        concertValidation.checkAllPlaceSeat(concert);
        concertValidation.checkSeatGrade(concert);
    }

    private Concert toModel(Concert concert) {
        Place place = placeService.getPlaceById(concert.placeId());

        return Concert.builder()
            .name(concert.name())
            .detailInfo(concert.detailInfo())
            .runningTime(concert.runningTime())
            .state(ConcertState.READY)
            .ticketingStartTime(concert.ticketingStartTime())
            .openTime(concert.openTime())
            .placeId(concert.placeId())
            .placeName(place.getName())
            .seats(toConcertSeatModels(concert))
            .rounds(List.copyOf(concert.rounds()))
            .seatGrades(List.copyOf(concert.seatGrades()))
            .build();
    }

    public List<ConcertSeat> toConcertSeatModels(Concert concert) {
        Map<Long, List<PlaceSeat>> allSeatsMap = getAllPlaceSeatsPort.getAllByPlaceId(concert.placeId()).stream().collect(groupingBy(PlaceSeat::getId));
        Map<String, List<ConcertSeatGrade>> gradesMap = concert.seatGrades().stream().collect(groupingBy(ConcertSeatGrade::name));

        return concert.seats().stream().map(seat -> {
                PlaceSeat foundSeat = allSeatsMap.get(seat.id()).get(0);
                return ConcertSeat.builder()
                    .columnNum(seat.columnNum())
                    .floor(foundSeat.getFloor())
                    .rowOrder(foundSeat.getRowOrder())
                    .state(ConcertSeatState.EMPTY)
                    .grade(gradesMap.get(seat.getGradeName()).get(0))
                    .build();
            }
        ).toList();
    }
}
