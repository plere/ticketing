package com.example.concertservice.place.application.port.out;

import com.example.concertservice.place.domain.Place;

import java.util.List;

public interface GetPlacePort {
    List<Place> getAll();

    List<Place> getAllByName(String name);

    Place getById(long id);
}
