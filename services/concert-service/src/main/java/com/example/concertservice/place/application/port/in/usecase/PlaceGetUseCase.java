package com.example.concertservice.place.application.port.in.usecase;

import com.example.concertservice.place.domain.Place;

import java.util.List;

public interface PlaceGetUseCase {
    List<Place> getAllPlaces();

    List<Place> getAllPlacesByName(String name);

    Place getPlaceById(long id);
}
