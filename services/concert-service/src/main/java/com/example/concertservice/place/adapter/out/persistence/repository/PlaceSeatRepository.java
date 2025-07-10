package com.example.concertservice.place.adapter.out.persistence.repository;

import com.example.concertservice.place.domain.PlaceSeat;

import java.util.List;

public interface PlaceSeatRepository {
    List<PlaceSeat> getAllByPlaceId(Long placeId);
}
