package com.example.reservationservice.reservation.adapter.out.web.concert.feign;


import com.example.reservationservice.reservation.adapter.out.web.concert.feign.dto.HoldSeatRequest;
import com.example.reservationservice.reservation.adapter.out.web.concert.feign.dto.ReleaseSeatRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
    name = "concertSeatFeignClient",
    url = "${external.client.concert-seat.url}"
)
public interface ConcertSeatFeignClient {
    @PostMapping("/internal/concerts/seats/hold")
    void holdSeats(HoldSeatRequest request);

    @DeleteMapping("/internal/concerts/seats/hold")
    void releaseSeats(ReleaseSeatRequest request);
}
