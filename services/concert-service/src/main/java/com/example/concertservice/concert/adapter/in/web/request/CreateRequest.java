package com.example.concertservice.concert.adapter.in.web.request;

import com.example.concertservice.concert.domain.Concert;
import com.example.concertservice.concert.domain.ConcertRound;
import com.example.concertservice.concert.domain.ConcertSeat;
import com.example.concertservice.concert.domain.ConcertSeatGrade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

public record CreateRequest(
    @NotBlank
    String name,

    String detailInfo,

    @NotNull
    @Positive
    Integer runningTime,

    @NotNull
    @Future
    LocalDateTime ticketingStartTime,

    @Future
    LocalDateTime openTime,

    @NotEmpty
    @Valid
    List<CreateRoundRequest> rounds,

    @Valid
    List<CreateSeatRequest> seats,

    @Valid
    List<CreateSeatGradeRequest> grades,

    @NotNull
    @Positive
    Long placeId
) {
    public record CreateRoundRequest(
        @NotNull
        @Future
        LocalDateTime startDateTime
    ) {
        public ConcertRound toModel(int sequenceNumber) {
            return ConcertRound.builder()
                .startDateTime(startDateTime)
                .sequenceNumber(sequenceNumber)
                .build();
        }
    }

    public record CreateSeatRequest(
        @NotNull
        @Positive
        Long id,
        @NotNull
        @Positive
        Integer columnNum,
        @NotBlank
        String grade
    ) {
        public ConcertSeat toModel() {
            return ConcertSeat.builder()
                .id(id)
                .columnNum(columnNum)
                .grade(ConcertSeatGrade.builder()
                    .name(grade)
                    .build())
                .build();
        }
    }

    public record CreateSeatGradeRequest(
        @NotBlank
        String name,
        @NotNull
        @Positive
        Integer price
    ) {
        public ConcertSeatGrade toModel() {
            return ConcertSeatGrade.builder()
                .name(name)
                .price(price)
                .build();
        }
    }

    public Concert toModel() {
        return Concert.builder()
            .name(name)
            .detailInfo(detailInfo)
            .runningTime(runningTime)
            .ticketingStartTime(ticketingStartTime)
            .openTime(openTime)
            .placeId(placeId)
            .seats(seats.stream().map(CreateSeatRequest::toModel).toList())
            .rounds(
                IntStream.range(0, rounds.size()).mapToObj(idx ->
                    rounds.get(idx).toModel(idx + 1)
                ).toList()
            )
            .seatGrades(grades.stream().map(CreateSeatGradeRequest::toModel).toList())
            .build();
    }
}
