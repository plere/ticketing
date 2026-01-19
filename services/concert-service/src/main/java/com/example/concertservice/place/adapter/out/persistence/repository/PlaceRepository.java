package com.example.concertservice.place.adapter.out.persistence.repository;

import com.example.concertservice.place.domain.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository {
    Place save(Place place);

    List<Place> getAll();

    List<Place> getAllByName(String name);

    Optional<Place> getById(long id);
}
