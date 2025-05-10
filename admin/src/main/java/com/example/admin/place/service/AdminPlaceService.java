package com.example.admin.place.service;

import com.example.admin.place.controller.dto.CreatePlaceRequest;
import com.example.admin.place.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPlaceService {
    private final PlaceRepository placeRepository;

    @Transactional
    public void createPlace(CreatePlaceRequest request) {
        placeRepository.save(request.toModel());
    }
}
