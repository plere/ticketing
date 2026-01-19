package com.example.concertservice.concert.adapter.in.web.response;

import com.example.concertservice.concert.domain.Concert;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertGetAllByPageableResponse(
    Long id,
    String name,
    LocalDateTime openTime,
    String placeName
) {
    public static ConcertGetAllByPageableResponse from(Concert concert) {
        return ConcertGetAllByPageableResponse.builder()
            .id(concert.id())
            .name(concert.name())
            .openTime(concert.openTime())
            .placeName(concert.placeName())
            .build();
    }
}
