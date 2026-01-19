package com.example.concertservice.place.adapter.out.persistence;

import com.example.concertservice.place.adapter.out.persistence.repository.PlaceRepository;
import com.example.concertservice.place.application.port.out.GetPlacePort;
import com.example.concertservice.place.application.port.out.SavePlacePort;
import com.example.concertservice.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlacePersistenceAdapter implements SavePlacePort, GetPlacePort {
    private final PlaceRepository placeRepository;

    @Override
    public Place save(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public List<Place> getAll() {
        return placeRepository.getAll();
    }

    @Override
    public List<Place> getAllByName(String name) {
        return placeRepository.getAllByName(name);
    }

    @Override
    public Place getById(long id) {
        return placeRepository.getById(id).orElseThrow();
    }
}
