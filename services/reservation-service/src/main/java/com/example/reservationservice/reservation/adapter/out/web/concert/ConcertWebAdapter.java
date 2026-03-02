package com.example.reservationservice.reservation.adapter.out.web.concert;

import com.example.reservationservice.reservation.adapter.out.web.concert.feign.ConcertFeignClient;
import com.example.reservationservice.reservation.application.port.out.GetConcertPort;
import com.example.reservationservice.reservation.domain.Concert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcertWebAdapter implements GetConcertPort {
    private final ConcertFeignClient concertFeignClient;

    @Override
    public Concert getOrElseThrow(long id) {
        return concertFeignClient.getConcert(id).getBody().toModel();
    }
}
