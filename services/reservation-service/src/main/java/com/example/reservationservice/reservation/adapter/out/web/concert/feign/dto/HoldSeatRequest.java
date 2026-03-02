package com.example.reservationservice.reservation.adapter.out.web.concert.feign.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class HoldSeatRequest {
    private Set<Long> seatIds;
}
