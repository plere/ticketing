package com.example.concertservice.place.adapter.out.persistence;

import com.example.concertservice.place.adapter.out.persistence.repository.PlaceSeatRepository;
import com.example.concertservice.place.application.port.out.GetAllPlaceSeatsPort;
import com.example.concertservice.place.domain.PlaceSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaceSeatPersistenceAdapter implements GetAllPlaceSeatsPort {
    private final PlaceSeatRepository placeSeatRepository;

    @Override
    public List<PlaceSeat> getAllByPlaceId(Long placeId) {
        return placeSeatRepository.getAllByPlaceId(placeId);
    }
}
