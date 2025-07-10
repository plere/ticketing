package com.example.concertservice.concert.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertRound(
    Long id,
    LocalDateTime startDateTime,
    Integer sequenceNumber
) {
}
