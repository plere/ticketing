package com.example.concertservice.place.adapter.out.persistence.repository;

import com.example.concertservice.place.domain.Place;

import java.util.List;

public interface PlaceRepository {
    Place save(Place place);

    List<Place> getAll();
}
