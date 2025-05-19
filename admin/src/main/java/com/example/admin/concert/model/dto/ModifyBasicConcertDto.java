package com.example.admin.concert.model.dto;

import com.example.admin.concert.model.ConcertRound;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ModifyBasicConcertDto(
    String name,
    String detailInfo,
    Integer runningTime,
    LocalDateTime ticketingStartTime,
    LocalDateTime openTime,
    List<ConcertRound> rounds
) {
}
