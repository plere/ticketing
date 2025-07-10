package com.example.concertservice.concert.application.port.out;


import com.example.concertservice.concert.domain.PlaceSeat;

import java.util.List;

public interface GetPlacePort {
    List<PlaceSeat> getAllSeatsByPlaceId(Long placeId);
}
