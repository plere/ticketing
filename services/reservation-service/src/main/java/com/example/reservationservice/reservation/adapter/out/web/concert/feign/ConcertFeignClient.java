package com.example.reservationservice.reservation.adapter.out.web.concert.feign;

import com.example.httpresponse.response.ResponseDto;
import com.example.reservationservice.reservation.adapter.out.web.concert.feign.dto.GetConcertResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "concertFeignClient",
    url = "${external.client.concert.url}"
)
public interface ConcertFeignClient {
    @GetMapping("/internal/concerts/{id}")
    ResponseDto<GetConcertResponse> getConcert(@PathVariable("id") Long id);
}
