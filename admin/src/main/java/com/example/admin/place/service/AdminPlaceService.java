package com.example.admin.place.service;

import com.example.admin.place.controller.dto.CreatePlaceRequest;
import com.example.admin.place.controller.dto.GetAllPlacesResponse;
import com.example.admin.place.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPlaceService {
    private final PlaceRepository placeRepository;

    public GetAllPlacesResponse getAllPlaces() {
        return GetAllPlacesResponse.from(placeRepository.findAll());
    }

    @Transactional
    public long createPlace(CreatePlaceRequest request) {
        return placeRepository.save(request.toModel()).getId();
    }
}
