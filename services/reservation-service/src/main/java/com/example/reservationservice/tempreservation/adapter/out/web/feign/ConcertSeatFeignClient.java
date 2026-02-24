package com.example.reservationservice.tempreservation.adapter.out.web.feign;


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
