package com.example.admin.concert.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminConcertCreateValidation {
    private final AdminConcertValidation adminConcertValidation;

    public void validate(ValidationConcert validationDto) {
        adminConcertValidation.checkRounds(validationDto);
        adminConcertValidation.checkTicketingTime(validationDto);
        adminConcertValidation.checkAllPlaceSeat(validationDto);
        adminConcertValidation.checkSeatGrade(validationDto);
    }
}
