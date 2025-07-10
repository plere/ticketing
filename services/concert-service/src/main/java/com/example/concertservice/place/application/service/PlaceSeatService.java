package com.example.concertservice.place.application.service;

import com.example.concertservice.place.application.port.in.usecase.PlaceSeatGetAllByPlaceIdUseCase;
import com.example.concertservice.place.application.port.out.GetAllPlaceSeatsPort;
import com.example.concertservice.place.domain.PlaceSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceSeatService implements PlaceSeatGetAllByPlaceIdUseCase {
    private final GetAllPlaceSeatsPort getAllPlaceSeatsPort;

    @Override
    public List<PlaceSeat> getAllByPlaceId(Long placeId) {
        return getAllPlaceSeatsPort.getAllByPlaceId(placeId);
    }
}
