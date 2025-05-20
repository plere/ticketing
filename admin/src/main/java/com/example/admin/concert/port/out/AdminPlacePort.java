package com.example.admin.concert.port.out;

import com.example.admin.place.model.PlaceSeat;
import com.example.admin.place.repository.PlaceSeatRepository;
import com.example.httpresponse.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.admin.concert.controller.dto.AdminConcertErrorResponseCode.PLACE_OR_SEATS_ERROR;

@Service
@RequiredArgsConstructor
public class AdminPlacePort {
    private final PlaceSeatRepository placeSeatRepository;

    public List<PlaceSeat> getAllSeatsByPlaceId(Long placeId) {
        List<PlaceSeat> seats = placeSeatRepository.findAllByPlaceId(placeId);

        if (seats.isEmpty()) {
            throw new BadRequestException(PLACE_OR_SEATS_ERROR, PLACE_OR_SEATS_ERROR.getErrorMessage());
        }

        return seats;
    }
}
