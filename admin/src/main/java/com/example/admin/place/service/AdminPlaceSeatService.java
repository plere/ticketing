package com.example.admin.place.service;

import com.example.admin.place.model.PlaceSeat;
import com.example.admin.place.repository.PlaceSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPlaceSeatService {
    private final PlaceSeatRepository placeSeatRepository;

    public List<PlaceSeat> getAllByPlaceId(Long placeId) {
        return placeSeatRepository.findAllByPlaceId(placeId);
    }
}
