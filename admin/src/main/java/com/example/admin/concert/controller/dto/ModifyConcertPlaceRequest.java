package com.example.admin.concert.controller.dto;

import com.example.admin.concert.model.ConcertSeat;
import com.example.admin.concert.model.ConcertSeatGrade;
import com.example.admin.concert.model.ConcertSeatState;
import com.example.admin.concert.model.dto.ModifyConcertPlaceDto;
import com.example.admin.concert.service.validation.ValidationConcert;
import com.example.admin.place.model.PlaceSeat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

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
        Integer rowOrder,
        @NotBlank
        String grade
    ) {
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


    public ModifyConcertPlaceDto toModifyDto(long concertId, List<PlaceSeat> allSeats) {
        Map<Long, List<PlaceSeat>> allSeatsMap = allSeats.stream().collect(groupingBy(PlaceSeat::getId));
        List<ConcertSeatGrade> gradeModels = grades.stream().map(ModifySeatGradeRequest::toModel).toList();
        Map<String, List<ConcertSeatGrade>> gradesMap = gradeModels.stream().collect(groupingBy(ConcertSeatGrade::getName));

        return ModifyConcertPlaceDto.builder()
            .seats(
                seats.stream().map(seat -> {
                        PlaceSeat foundSeat = allSeatsMap.get(seat.id).get(0);
                        return ConcertSeat.builder()
                            .columnNum(seat.rowOrder)
                            .rowNum(foundSeat.getRowName())
                            .floor(foundSeat.getFloor())
                            .state(ConcertSeatState.EMPTY)
                            .grade(gradesMap.get(seat.grade).get(0))
                            .concertId(concertId)
                            .build();
                    }
                ).toList()
            )
            .seatGrades(gradeModels)
            .placeId(placeId)
            .build();
    }

    public ValidationConcert toValidationDto() {
        return ValidationConcert.builder()
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
