package com.example.concertservice.concert.adapter.in.web.response.user;

import com.example.concertservice.concert.domain.ConcertSeat;
import com.example.concertservice.concert.domain.ConcertSeatGrade;
import com.example.concertservice.concert.domain.ConcertSeatState;
import lombok.Builder;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record UserGetAllConcertSeatCountResponse(
    List<UserConcertSeatGradeResponse> grades,
    List<UserConcertSeatResponse> seats
) {
    public static UserGetAllConcertSeatCountResponse from(List<ConcertSeat> concertSeats) {
        return UserGetAllConcertSeatCountResponse.builder()
            .grades(
                UserConcertSeatGradeResponse.from(concertSeats.stream()
                    .filter(seat -> seat.state() == ConcertSeatState.EMPTY)
                    .map(ConcertSeat::grade)
                    .toList())
            )
            .seats(UserConcertSeatResponse.from(concertSeats))
            .build();
    }

    @Builder
    public record UserConcertSeatGradeResponse(
        String gradeName,
        int price,
        int emptySeatCount
    ) {
        public static List<UserConcertSeatGradeResponse> from(List<ConcertSeatGrade> grades) {
            return grades.stream().collect(Collectors.groupingBy(ConcertSeatGrade::name)).entrySet().stream()
                .map(grade -> UserConcertSeatGradeResponse.builder()
                    .gradeName(grade.getKey())
                    .price(grade.getValue().get(0).price())
                    .emptySeatCount(grade.getValue().size())
                    .build()
                )
                .sorted(Comparator.comparing(UserConcertSeatGradeResponse::price).reversed())
                .toList();
        }
    }

    @Builder
    public record UserConcertSeatResponse(
        int floor,
        List<UserConcertRowSeatResponse> rows
    ) {
        @Builder
        public record UserConcertRowSeatResponse(
            int row,
            List<UserConcertColumnSeatResponse> columns
        ) {
            @Builder
            public record UserConcertColumnSeatResponse(
                int column,
                SEAT_STATE state
            ) {
                public enum SEAT_STATE {
                    EMPTY,
                    RESERVED
                }
            }
        }

        public static List<UserConcertSeatResponse> from(List<ConcertSeat> seats) {
            return seats.stream().collect(Collectors.groupingBy(ConcertSeat::floor)).entrySet().stream().map(
                seat -> UserConcertSeatResponse.builder()
                    .floor(seat.getKey())
                    .rows(
                        seat.getValue().stream().collect(Collectors.groupingBy(ConcertSeat::rowOrder)).entrySet().stream().map(
                            rowSeat -> UserConcertRowSeatResponse.builder()
                                .row(rowSeat.getKey())
                                .columns(rowSeat.getValue().stream().map(columnSeat -> UserConcertRowSeatResponse.UserConcertColumnSeatResponse.builder()
                                    .column(columnSeat.columnNum())
                                    .state(columnSeat.state() == ConcertSeatState.EMPTY ? UserConcertRowSeatResponse.UserConcertColumnSeatResponse.SEAT_STATE.EMPTY : UserConcertRowSeatResponse.UserConcertColumnSeatResponse.SEAT_STATE.RESERVED)
                                    .build()).toList())
                                .build()
                        ).toList()
                    )
                    .build()
            ).toList();
        }
    }
}
