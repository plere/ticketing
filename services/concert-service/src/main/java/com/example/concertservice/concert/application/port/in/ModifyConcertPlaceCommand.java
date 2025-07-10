package com.example.concertservice.concert.application.port.in;

import com.example.concertservice.concert.domain.Concert;
import com.example.concertservice.concert.domain.ConcertSeat;
import com.example.concertservice.concert.domain.ConcertSeatGrade;
import com.example.concertservice.concert.domain.ConcertSeatState;
import com.example.concertservice.place.domain.PlaceSeat;
import lombok.Builder;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Builder
public record ModifyConcertPlaceCommand(
    long id,
    Long placeId,
    List<ConcertSeat> seats,
    List<ConcertSeatGrade> seatGrades
) {
    public Concert toModel(Concert concert, List<PlaceSeat> placeSeats) {
        return Concert.builder()
            .id(id)
            .name(concert.name())
            .detailInfo(concert.detailInfo())
            .runningTime(concert.runningTime())
            .state(concert.state())
            .ticketingStartTime(concert.ticketingStartTime())
            .openTime(concert.openTime())
            .placeId(placeId)
            .seats(toConcertSeatModels(placeSeats))
            .rounds(List.copyOf(concert.rounds()))
            .seatGrades(List.copyOf(seatGrades))
            .build();
    }

    public List<ConcertSeat> toConcertSeatModels(List<PlaceSeat> placeSeats) {
        Map<Long, List<PlaceSeat>> allSeatsMap = placeSeats.stream().collect(groupingBy(PlaceSeat::getId));
        Map<String, List<ConcertSeatGrade>> gradesMap = seatGrades().stream().collect(groupingBy(ConcertSeatGrade::name));

        return seats().stream().map(seat -> {
                PlaceSeat foundSeat = allSeatsMap.get(seat.id()).get(0);
                return ConcertSeat.builder()
                    .id(seat.id())
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
