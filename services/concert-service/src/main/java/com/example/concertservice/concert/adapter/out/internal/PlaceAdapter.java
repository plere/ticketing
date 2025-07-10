package com.example.concertservice.concert.adapter.out.internal;

import com.example.concertservice.concert.application.port.out.GetPlacePort;
import com.example.concertservice.concert.domain.PlaceSeat;
import com.example.concertservice.place.application.port.in.usecase.PlaceSeatGetAllByPlaceIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaceAdapter implements GetPlacePort {
    private final PlaceSeatGetAllByPlaceIdUseCase placeSeatGetAllByPlaceIdUseCase;

    @Override
    public List<PlaceSeat> getAllSeatsByPlaceId(Long placeId) {
        return placeSeatGetAllByPlaceIdUseCase.getAllByPlaceId(placeId)
            .stream()
            .map(PlaceSeatMapper::mapToInConcertModel)
            .toList();
    }
}
