package com.example.admin.place.service;

import com.example.admin.place.controller.dto.GetAllSeatsByPlaceIdResponse;
import com.example.admin.place.repository.PlaceSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPlaceSeatService {
    private final PlaceSeatRepository placeSeatRepository;

    public GetAllSeatsByPlaceIdResponse getAllByPlaceId(Long placeId) {
        return GetAllSeatsByPlaceIdResponse.from(placeSeatRepository.findAllByPlaceId(placeId));
    }
}
