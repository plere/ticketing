package com.example.concertservice.place.application.service;

import com.example.concertservice.place.application.port.in.usecase.PlaceCreateUseCase;
import com.example.concertservice.place.application.port.in.usecase.PlaceGetUseCase;
import com.example.concertservice.place.application.port.out.GetPlacePort;
import com.example.concertservice.place.application.port.out.SavePlacePort;
import com.example.concertservice.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService implements PlaceCreateUseCase, PlaceGetUseCase {
    private final SavePlacePort savePlacePort;
    private final GetPlacePort getPlacePort;

    @Override
    @Transactional
    public long create(Place place) {
        return savePlacePort.save(place).getId();
    }

    @Override
    public List<Place> getAllPlaces() {
        return getPlacePort.getAll();
    }

    @Override
    public List<Place> getAllPlacesByName(String name) {
        return getPlacePort.getAllByName(name);
    }

    @Override
    public Place getPlaceById(long id) {
        return getPlacePort.getById(id);
    }
}
