package com.example.reservationservice.tempreservation.adapter.out.web;

import com.example.reservationservice.tempreservation.domain.Place;
import com.example.reservationservice.tempreservation.application.port.out.GetPlacePort;
import org.springframework.stereotype.Component;

@Component
public class PlaceWebAdapter implements GetPlacePort {
    @Override
    public Place getOrElseThrow(long id) {
        return Place.builder()
            .id(6L)
            .name("올림픽공원")
            .address("seoul")
            .build();
    }
}
