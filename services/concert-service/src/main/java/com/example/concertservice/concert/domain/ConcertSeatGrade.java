package com.example.concertservice.concert.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConcertSeatGrade(
    Long id,
    LocalDateTime createdAt,
    String name,
    Integer price
) {
}
