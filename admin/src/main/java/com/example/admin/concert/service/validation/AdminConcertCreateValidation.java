package com.example.admin.concert.service.validation;

import com.example.admin.concert.controller.dto.CreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminConcertCreateValidation {
    private final AdminConcertValidation adminConcertValidation;

    public void validate(CreateRequest request) {
        ValidationConcert validationDto = request.toValidationDto();
        adminConcertValidation.checkOpenTime(validationDto);
        adminConcertValidation.checkTicketingTime(validationDto);
        adminConcertValidation.checkAllPlaceSeat(validationDto);
        adminConcertValidation.checkSeatGrade(validationDto);
    }
}
