package com.example.reservationservice.reservation.adapter.out.web;

import com.example.reservationservice.reservation.adapter.out.web.feign.ConcertSeatFeignClient;
import com.example.reservationservice.reservation.adapter.out.web.feign.HoldSeatRequest;
import com.example.reservationservice.reservation.adapter.out.web.feign.ReleaseSeatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ConcertSeatFeignAdapter implements ConcertSeatWebAdapter {
    private final ConcertSeatFeignClient concertSeatFeignClient;

    @Override
    public void holdSeats(Set<Long> seatIds) {
        concertSeatFeignClient.holdSeats(HoldSeatRequest.builder()
            .seatIds(Set.copyOf(seatIds))
            .build());
    }

    @Override
    public void releaseSeats(Set<Long> seatIds) {
        //todo 중요하니까 재시도로직 + 재시도도 실패하면 로그발생
        concertSeatFeignClient.releaseSeats(ReleaseSeatRequest.builder()
            .seatIds(Set.copyOf(seatIds))
            .build());
    }
}
