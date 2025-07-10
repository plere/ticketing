package com.example.concertservice.place.application.port.in.usecase;

import com.example.concertservice.place.domain.PlaceSeat;

import java.util.List;

public interface PlaceSeatGetAllByPlaceIdUseCase {
    List<PlaceSeat> getAllByPlaceId(Long placeId);
}
