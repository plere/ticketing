package com.example.concertservice.concert.adapter.in.web.response.user;

import com.example.concertservice.concert.domain.ConcertSeat;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record UserGetAllEmptyConcertSeatCountResponse(
    List<UserConcertSeatResponse> emptySeats
) {
    public static UserGetAllEmptyConcertSeatCountResponse from(List<ConcertSeat> concertSeats) {
        return UserGetAllEmptyConcertSeatCountResponse.builder()
            .emptySeats(
                concertSeats.stream().collect(Collectors.groupingBy(ConcertSeat::getGradeName)).entrySet().stream().map(seat -> UserConcertSeatResponse.builder()
                        .gradeName(seat.getKey())
                        .count(seat.getValue().size())
                        .build())
                    .toList())
            .build();
    }

    @Builder
    public record UserConcertSeatResponse(
        String gradeName,
        int count
    ) {
    }
}
