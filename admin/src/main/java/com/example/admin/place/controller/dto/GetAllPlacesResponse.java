package com.example.admin.place.controller.dto;

import com.example.admin.place.model.Place;
import lombok.Builder;

import java.util.List;

@Builder
public record GetAllPlacesResponse(
    List<PlaceResponse> places
) {
    @Builder
    public record PlaceResponse(
        long id,
        String name
    ) {
    }

    public static GetAllPlacesResponse from(List<Place> places) {
        return GetAllPlacesResponse.builder()
            .places(places.stream().map(
                place -> PlaceResponse.builder()
                    .id(place.getId())
                    .name(place.getName())
                    .build()
            ).toList())
            .build();
    }
}
