package com.example.reservationservice.reservation.adapter.out.web.feign.dto;

import com.example.reservationservice.reservation.domain.Concert;
import com.example.reservationservice.reservation.domain.ConcertRound;
import com.example.reservationservice.reservation.domain.ConcertSeat;
import com.example.reservationservice.reservation.domain.ConcertSeatGrade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetConcertResponse {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String detailInfo;
    private int runningTime;
    private String state;
    private LocalDateTime ticketingStartTime;
    private LocalDateTime openTime;
    private Long placeId;
    private String placeName;
    private List<ConcertSeatResponse> seats;
    private List<ConcertRoundResponse> rounds;
    private List<ConcertSeatGradeResponse> seatGrades;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ConcertSeatResponse {
        private Long id;
        private LocalDateTime createdAt;
        private int floor;
        private Integer rowOrder;
        private int columnNum;
        private String state;
        private ConcertSeatGradeResponse grade;
        private Long roundId;

        public ConcertSeat toModel() {
            return ConcertSeat.builder()
                .id(id)
                .floor(floor)
                .rowOrder(rowOrder)
                .columnNum(columnNum)
                .grade(grade.toModel())
                .build();
        }
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ConcertSeatGradeResponse {
        private Long id;
        private LocalDateTime createdAt;
        private String name;
        Integer price;

        public ConcertSeatGrade toModel() {
            return ConcertSeatGrade.builder()
                .id(id)
                .name(name)
                .price(price)
                .build();
        }
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ConcertRoundResponse {
        private Long id;
        private LocalDateTime startDateTime;
        private Integer sequenceNumber;

        public ConcertRound toModel() {
            return ConcertRound.builder()
                .id(id)
                .startDateTime(startDateTime)
                .sequenceNumber(sequenceNumber)
                .build();
        }
    }

    public Concert toModel() {
        return Concert.builder()
            .id(id)
            .name(name)
            .detailInfo(detailInfo)
            .runningTime(runningTime)
            .ticketingStartTime(ticketingStartTime)
            .openTime(openTime)
            .placeId(placeId)
            .seats(seats.stream().map(ConcertSeatResponse::toModel).toList())
            .rounds(rounds.stream().map(ConcertRoundResponse::toModel).toList())
            .seatGrades(seatGrades.stream().map(ConcertSeatGradeResponse::toModel).toList())
            .build();
    }
}
