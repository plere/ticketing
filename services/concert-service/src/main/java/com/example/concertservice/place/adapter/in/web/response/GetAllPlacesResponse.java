package com.example.concertservice.place.adapter.in.web.response;

import com.example.concertservice.place.domain.Place;
import lombok.Builder;

import java.util.List;

@Builder
public record GetAllPlacesResponse(
    List<PlaceResponse> places
) {
    @Builder
    public record PlaceResponse(
        long id,
        String name,
        String address,
        int totalSeatsCount
    ) {
    }

    public static GetAllPlacesResponse from(List<Place> places) {
        return GetAllPlacesResponse.builder()
            .places(places.stream().map(
                place -> PlaceResponse.builder()
                    .id(place.getId())
                    .name(place.getName())
                    .address(place.getAddress())
                    .totalSeatsCount(place.getTotalSeatsCount())
                    .build()
            ).toList())
            .build();
    }
}
