package com.example.concertservice.concert.adapter.in.web.response;

import com.example.concertservice.concert.domain.Concert;
import com.example.concertservice.concert.domain.ConcertRound;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ConcertGetAllByPageableResponse(
    Long id,
    String name,
    String detailInfo,
    int runningTime,
    LocalDateTime ticketingStartTime,
    LocalDateTime openTime,
    Long placeId,
    List<ConcertRound> rounds
) {
    public static ConcertGetAllByPageableResponse from(Concert concert) {
        return ConcertGetAllByPageableResponse.builder()
            .id(concert.id())
            .name(concert.name())
            .detailInfo(concert.detailInfo())
            .runningTime(concert.runningTime())
            .ticketingStartTime(concert.ticketingStartTime())
            .openTime(concert.openTime())
            .placeId(concert.placeId())
            .rounds(List.copyOf(concert.rounds()))
            .build();
    }
}
