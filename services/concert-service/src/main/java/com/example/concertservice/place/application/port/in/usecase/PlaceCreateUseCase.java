package com.example.concertservice.place.application.port.in.usecase;

import com.example.concertservice.place.domain.Place;

public interface PlaceCreateUseCase {
    long create(Place place);
}
