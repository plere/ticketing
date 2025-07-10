package com.example.concertservice.concert.adapter.in.web.request;

import com.example.concertservice.concert.application.port.in.ModifyConcertPlaceCommand;
import com.example.concertservice.concert.domain.ConcertSeat;
import com.example.concertservice.concert.domain.ConcertSeatGrade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record ModifyConcertPlaceRequest(
    @Valid
    List<ModifySeatRequest> seats,

    @Valid
    List<ModifySeatGradeRequest> grades,

    @NotNull
    @Positive
    Long placeId
) {
    public record ModifySeatRequest(
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

    public record ModifySeatGradeRequest(
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


    public ModifyConcertPlaceCommand toModifyCommand(long concertId) {
        return ModifyConcertPlaceCommand.builder()
            .id(concertId)
            .seats(seats.stream().map(ModifySeatRequest::toModel).toList())
            .seatGrades(grades.stream().map(ModifySeatGradeRequest::toModel).toList())
            .placeId(placeId)
            .build();
    }
}
