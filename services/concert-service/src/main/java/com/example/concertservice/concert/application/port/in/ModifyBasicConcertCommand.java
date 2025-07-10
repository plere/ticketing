package com.example.concertservice.concert.application.port.in;

import com.example.concertservice.concert.domain.Concert;
import com.example.concertservice.concert.domain.ConcertRound;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ModifyBasicConcertCommand(
    long id,
    String name,
    String detailInfo,
    Integer runningTime,
    LocalDateTime ticketingStartTime,
    LocalDateTime openTime,
    List<ConcertRound> rounds
) {
    public Concert toModel(Concert concert) {
        return Concert.builder()
            .id(id)
            .name(name)
            .detailInfo(detailInfo)
            .runningTime(runningTime)
            .ticketingStartTime(ticketingStartTime)
            .openTime(openTime)
            .rounds(List.copyOf(rounds))
            .state(concert.state())
            .placeId(concert.placeId())
            .seats(List.copyOf(concert.seats()))
            .seatGrades(List.copyOf(concert.seatGrades()))
            .build();
    }
}
