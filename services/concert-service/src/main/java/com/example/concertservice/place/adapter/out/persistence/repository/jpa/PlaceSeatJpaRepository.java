package com.example.concertservice.place.adapter.out.persistence.repository.jpa;

import com.example.concertservice.place.adapter.out.persistence.entity.PlaceSeatEntityMapper;
import com.example.concertservice.place.adapter.out.persistence.repository.PlaceSeatRepository;
import com.example.concertservice.place.domain.PlaceSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaceSeatJpaRepository implements PlaceSeatRepository {
    private final SpringDataJpaPlaceSeatRepository placeSeatRepository;

    @Override
    public List<PlaceSeat> getAllByPlaceId(Long placeId) {
        return placeSeatRepository.findAllByPlaceId(placeId).stream()
            .map(PlaceSeatEntityMapper::mapToModel)
            .toList();
    }
}
