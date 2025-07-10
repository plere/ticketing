package com.example.concertservice.place.application.port.in.usecase;

import com.example.concertservice.place.domain.Place;

import java.util.List;

public interface PlaceGetAllUseCase {
    List<Place> getAllPlaces();
}
