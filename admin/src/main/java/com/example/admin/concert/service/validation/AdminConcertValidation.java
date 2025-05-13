package com.example.admin.concert.service.validation;

import com.example.admin.common.exception.BadRequestException;
import com.example.admin.concert.controller.dto.CreateRequest;
import com.example.admin.place.model.PlaceSeat;
import com.example.admin.place.repository.PlaceSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.admin.concert.controller.dto.AdminConcertErrorResponseCode.*;
import static java.util.stream.Collectors.groupingBy;

@Component
@RequiredArgsConstructor
public class AdminConcertValidation {
    private final PlaceSeatRepository placeSeatRepository;

    public void checkOpenTime(ValidationConcert dto) {
        if (dto.openTime() != null) {
            checkPlaceSeat(dto);
        }
    }

    public void checkTicketingTime(ValidationConcert dto) {
        if (!dto.isTicketingTimeAfterOpenTime()) {
            throw new BadRequestException(TICKETING_TIME_CANT_BEFORE_OPEN_TIME_ERROR, TICKETING_TIME_CANT_BEFORE_OPEN_TIME_ERROR.getErrorMessage());
        }
    }

    public void checkAllPlaceSeat(ValidationConcert dto) {
        if (!dto.seats().isEmpty()) {
            checkPlaceSeat(dto);
        }
    }

    public void checkSeatGrade(ValidationConcert dto) {
        Set<String> gradeSet = new HashSet<>();
        Set<String> seatGradeSet = new HashSet<>();

        dto.grades().forEach(grade -> gradeSet.add(grade.name()));
        dto.seats().forEach(seat -> seatGradeSet.add(seat.grade()));

        if (gradeSet.size() != seatGradeSet.size()) {
            throw new BadRequestException(CREATE_CONCERT_GRADE_INPUT_ERROR, CREATE_CONCERT_GRADE_INPUT_ERROR.getErrorMessage());
        }

        gradeSet.forEach(grade -> {
            if (!seatGradeSet.contains(grade)) {
                throw new BadRequestException(CREATE_CONCERT_GRADE_INPUT_ERROR, CREATE_CONCERT_GRADE_INPUT_ERROR.getErrorMessage());
            }
        });
    }

    private void checkPlaceSeat(ValidationConcert dto) {
        List<PlaceSeat> allSeats = placeSeatRepository.findAllByPlaceId(dto.placeId());

        if (registeredSeatCount(allSeats) != dto.seats().size()) {
            throw new BadRequestException(NOT_EQUAL_SEATS_SIZE_TO_PLACE_ID_ERROR, NOT_EQUAL_SEATS_SIZE_TO_PLACE_ID_ERROR.getErrorMessage());
        }

        isValidSeat(dto, allSeats);
    }

    private int registeredSeatCount(List<PlaceSeat> allSeats) {
        return allSeats.stream().mapToInt(PlaceSeat::getRowCount).sum();
    }

    private void isValidSeat(ValidationConcert dto, List<PlaceSeat> allSeats) {
        Map<Long, List<PlaceSeat>> seatsMap = allSeats.stream().collect(groupingBy(PlaceSeat::getId));
        Set<CreateRequest.CreateSeatRequest> requestSeats = new HashSet<>();

        dto.seats().forEach(seat -> {
            List<PlaceSeat> foundPlaceSeat = seatsMap.get(seat.id());
            if (foundPlaceSeat == null || foundPlaceSeat.get(0).getRowCount() < seat.rowOrder()) {
                throw new BadRequestException(NOT_MATCH_SEATS_INFO_TO_PLACE_ID_ERROR, NOT_MATCH_SEATS_INFO_TO_PLACE_ID_ERROR.getErrorMessage());
            }
            requestSeats.add(new CreateRequest.CreateSeatRequest(seat.id(), seat.rowOrder(), null));
        });

        if (requestSeats.size() != registeredSeatCount(allSeats)) {
            throw new BadRequestException(NOT_MATCH_SEATS_INFO_TO_PLACE_ID_ERROR, NOT_MATCH_SEATS_INFO_TO_PLACE_ID_ERROR.getErrorMessage());
        }
    }
}
