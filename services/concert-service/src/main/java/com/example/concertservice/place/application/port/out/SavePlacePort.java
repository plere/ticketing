package com.example.concertservice.place.application.port.out;

import com.example.concertservice.place.domain.Place;

public interface SavePlacePort {
    Place save(Place place);
}
