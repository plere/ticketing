package com.example.admin.concert.model.dto;

import com.example.admin.concert.model.ConcertSeat;
import com.example.admin.concert.model.ConcertSeatGrade;
import lombok.Builder;

import java.util.List;

@Builder
public record ModifyConcertPlaceDto(
    Long placeId,
    List<ConcertSeat> seats,
    List<ConcertSeatGrade> seatGrades
) {
}
