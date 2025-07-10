package com.example.concertservice.place.application.port.out;

import com.example.concertservice.place.domain.PlaceSeat;

import java.util.List;

public interface GetAllPlaceSeatsPort {
    List<PlaceSeat> getAllByPlaceId(Long placeId);
}
