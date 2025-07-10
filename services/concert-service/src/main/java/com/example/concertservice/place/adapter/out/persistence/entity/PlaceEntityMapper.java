package com.example.concertservice.place.adapter.out.persistence.entity;

import com.example.concertservice.place.domain.Place;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PlaceEntityMapper {

    public Place mapToModel(PlaceEntity entity) {
        return Place.builder()
            .id(entity.getId())
            .name(entity.getName())
            .address(entity.getAddress())
            .totalSeatsCount(entity.getTotalSeatsCount())
            .seats(entity.getSeats().stream().map(PlaceSeatEntityMapper::mapToModel).toList())
            .build();
    }

    public PlaceEntity mapToEntity(Place domain) {
        return PlaceEntity.builder()
            .id(domain.getId())
            .name(domain.getName())
            .address(domain.getAddress())
            .totalSeatsCount(domain.getTotalSeatsCount())
            .seats(domain.getSeats())
            .build();
    }
}
