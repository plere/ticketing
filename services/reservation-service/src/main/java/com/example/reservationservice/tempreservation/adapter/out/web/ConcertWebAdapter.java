package com.example.reservationservice.tempreservation.adapter.out.web;

import com.example.reservationservice.tempreservation.adapter.out.web.feign.ConcertFeignClient;
import com.example.reservationservice.tempreservation.model.Concert;
import com.example.reservationservice.tempreservation.port.out.GetConcertPort;
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
