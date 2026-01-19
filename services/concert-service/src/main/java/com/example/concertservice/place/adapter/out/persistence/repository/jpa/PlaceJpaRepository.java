package com.example.concertservice.place.adapter.out.persistence.repository.jpa;

import com.example.concertservice.place.adapter.out.persistence.entity.PlaceEntityMapper;
import com.example.concertservice.place.adapter.out.persistence.repository.PlaceRepository;
import com.example.concertservice.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlaceJpaRepository implements PlaceRepository {
    private final SpringDataJpaPlaceRepository placeRepository;

    @Override
    public Place save(Place place) {
        return PlaceEntityMapper.mapToModel(
            placeRepository.save(PlaceEntityMapper.mapToEntity(place))
        );
    }

    @Override
    public List<Place> getAll() {
        return placeRepository.findAll().stream().map(PlaceEntityMapper::mapToModel).toList();
    }

    @Override
    public List<Place> getAllByName(String name) {
        return placeRepository.findByNameContains(name).stream().map(PlaceEntityMapper::mapToModel).toList();
    }

    @Override
    public Optional<Place> getById(long id) {
        return placeRepository.findById(id).map(PlaceEntityMapper::mapToModel);
    }
}
