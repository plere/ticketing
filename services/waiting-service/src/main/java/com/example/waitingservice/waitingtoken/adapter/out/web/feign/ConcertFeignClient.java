package com.example.waitingservice.waitingtoken.adapter.out.web.feign;


import com.example.httpresponse.response.ResponseDto;
import com.example.waitingservice.waitingtoken.model.ConcertState;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "concertFeignClient",
    url = "${external.client.concert.url}"
)
public interface ConcertFeignClient {
    @GetMapping("/internal/concerts/{concertId}/state")
    ResponseDto<ConcertState> getState(@PathVariable long concertId);

    @GetMapping("/internal/concerts/{concertId}/rounds/{roundId}")
    ResponseDto<Boolean> isIncluded(@PathVariable long concertId, @PathVariable long roundId);
}
