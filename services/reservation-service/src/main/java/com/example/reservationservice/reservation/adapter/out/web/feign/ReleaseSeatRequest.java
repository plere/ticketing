package com.example.reservationservice.reservation.adapter.out.web.feign;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class ReleaseSeatRequest {
    private Set<Long> seatIds;
}
