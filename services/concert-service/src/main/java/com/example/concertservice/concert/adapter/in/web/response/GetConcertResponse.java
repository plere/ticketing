package com.example.concertservice.concert.adapter.in.web.response;

import com.example.concertservice.concert.domain.Concert;
import com.example.concertservice.concert.domain.ConcertRound;
import com.example.concertservice.concert.domain.ConcertSeatGrade;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GetConcertResponse(
    long id,
    String name,
    String detailInfo,
    int runningTime,
    LocalDateTime ticketingStartTime,
    Long placeId,
    String placeName,
    List<GetConcertRoundResponse> rounds,
    List<GetConcertSeatGradeResponse> seatGrades
) {
    @Builder
    public record GetConcertRoundResponse(
        Long id,
        LocalDateTime startDateTime,
        Integer sequenceNumber
    ) {
        public static GetConcertRoundResponse from(ConcertRound concertRound) {
            return GetConcertRoundResponse.builder()
                .id(concertRound.id())
                .startDateTime(concertRound.startDateTime())
                .sequenceNumber(concertRound.sequenceNumber())
                .build();
        }
    }

    @Builder
    public record GetConcertSeatGradeResponse(
        String name,
        Integer price
    ) {
        public static GetConcertSeatGradeResponse from(ConcertSeatGrade concertSeatGrade) {
            return GetConcertSeatGradeResponse.builder()
                .name(concertSeatGrade.name())
                .price(concertSeatGrade.price())
                .build();
        }
    }

    public static GetConcertResponse from(Concert concert) {
        return GetConcertResponse.builder()
            .id(concert.id())
            .name(concert.name())
            .detailInfo(concert.detailInfo())
            .runningTime(concert.runningTime())
            .ticketingStartTime(concert.ticketingStartTime())
            .placeId(concert.placeId())
            .placeName(concert.placeName())
            .rounds(concert.rounds().stream().map(GetConcertRoundResponse::from).toList())
            .seatGrades(concert.seatGrades().stream().map(GetConcertSeatGradeResponse::from).toList())
            .build();
    }
}
