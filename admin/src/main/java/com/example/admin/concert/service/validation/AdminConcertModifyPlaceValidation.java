package com.example.admin.concert.service.validation;

import com.example.admin.concert.controller.dto.ModifyConcertPlaceRequest;
import com.example.admin.concert.model.Concert;
import com.example.admin.concert.repository.AdminConcertRepository;
import com.example.httpresponse.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.admin.concert.controller.dto.AdminConcertErrorResponseCode.MODIFY_CONCERT_STATE_ERROR;
import static com.example.admin.concert.controller.dto.AdminConcertErrorResponseCode.NOT_FOUND_CONCERT_ERROR;

@Component
@RequiredArgsConstructor
public class AdminConcertModifyPlaceValidation {
    private final AdminConcertRepository adminConcertRepository;
    private final AdminConcertValidation adminConcertValidation;

    public void validate(long id, ModifyConcertPlaceRequest request) {
        Concert concert = adminConcertRepository.findById(id)
            .orElseThrow(() -> new BadRequestException(NOT_FOUND_CONCERT_ERROR, NOT_FOUND_CONCERT_ERROR.getErrorMessage()));

        if (!concert.getState().isModifiable()) {
            throw new BadRequestException(MODIFY_CONCERT_STATE_ERROR, MODIFY_CONCERT_STATE_ERROR.getErrorMessage());
        }

        ValidationConcert validationDto = request.toValidationDto();
        adminConcertValidation.checkAllPlaceSeat(validationDto);
        adminConcertValidation.checkSeatGrade(validationDto);
    }
}
