package com.example.admin.concert.controller.dto;

import com.example.admin.concert.model.*;
import com.example.admin.concert.service.validation.ValidationConcert;
import com.example.admin.place.model.PlaceSeat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

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
        Integer rowOrder,
        @NotBlank
        String grade
    ) {
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

        List<ConcertRound> list = IntStream.range(0, rounds.size()).mapToObj(idx ->
            rounds.get(idx).toModel(idx + 1)
        ).toList();
        return Concert.builder()
            .name(name)
            .detailInfo(detailInfo)
            .runningTime(runningTime)
            .state(ConcertState.READY)
            .ticketingStartTime(ticketingStartTime)
            .openTime(openTime)
            .placeId(placeId)
            .rounds(
                list
            )
            .seatGrades(
                grades.stream().map(CreateSeatGradeRequest::toModel).toList()
            )
            .build();
    }

    public List<ConcertSeat> toConcertSeatModels(List<PlaceSeat> allSeats, List<ConcertSeatGrade> allGrades) {
        Map<Long, List<PlaceSeat>> allSeatsMap = allSeats.stream().collect(groupingBy(PlaceSeat::getId));
        Map<String, List<ConcertSeatGrade>> gradesMap = allGrades.stream().collect(groupingBy(ConcertSeatGrade::getName));

        return seats.stream().map(seat -> {
                PlaceSeat foundSeat = allSeatsMap.get(seat.id).get(0);
                return ConcertSeat.builder()
                    .columnNum(seat.rowOrder)
                    .rowNum(foundSeat.getRowName())
                    .floor(foundSeat.getFloor())
                    .state(ConcertSeatState.EMPTY)
                    .grade(gradesMap.get(seat.grade).get(0))
                    .build();
            }
        ).toList();
    }

    public ValidationConcert toValidationDto() {
        return ValidationConcert.builder()
            .name(name)
            .detailInfo(detailInfo)
            .runningTime(runningTime)
            .ticketingStartTime(ticketingStartTime)
            .openTime(openTime)
            .rounds(
                rounds.stream()
                    .map(round -> ValidationConcert.ValidationRound.builder()
                        .startDateTime(round.startDateTime)
                        .build())
                    .toList()
            )
            .seats(
                seats.stream()
                    .map(seat -> ValidationConcert.ValidationSeat.builder()
                        .id(seat.id)
                        .rowOrder(seat.rowOrder)
                        .grade(seat.grade)
                        .build())
                    .toList()
            )
            .grades(
                grades.stream()
                    .map(grade -> ValidationConcert.ValidationSeatGrade.builder()
                        .name(grade.name)
                        .price(grade.price)
                        .build())
                    .toList()
            )
            .placeId(placeId)
            .build();
    }
}
