package com.example.admin.concert.service.validation;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ValidationConcert(
    Long id,
    String name,
    String detailInfo,
    Integer runningTime,
    LocalDateTime ticketingStartTime,
    LocalDateTime openTime,
    List<ValidationRound> rounds,
    List<ValidationSeat> seats,
    List<ValidationSeatGrade> grades,
    Long placeId
) {
    @Builder
    public record ValidationRound(
        LocalDateTime startDateTime
    ) {
    }

    @Builder
    public record ValidationSeat(
        Long id,
        Integer rowOrder,
        String grade
    ) {
    }

    @Builder
    public record ValidationSeatGrade(
        String name,
        Integer price
    ) {
    }

    public boolean isTicketingTimeAfterOpenTime() {
        if (ticketingStartTime != null
            && openTime != null
            && ticketingStartTime.isBefore(openTime)) {
            return false;
        }
        return true;
    }
}
