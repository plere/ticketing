package com.example.reservationservice.tempreservation.service;

import com.example.httpresponse.exception.BadRequestException;
import com.example.reservationservice.tempreservation.model.TempReservation;
import com.example.reservationservice.tempreservation.port.out.GetTempReservationPort;
import com.example.reservationservice.tempreservation.port.out.SaveTempReservationPort;
import com.example.reservationservice.tempreservation.service.validation.TempReservationValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.httpresponse.exception.CommonErrorCode.ARGUMENT_ERROR;

@Component
@RequiredArgsConstructor
public class TempReservationService {
    private final GetTempReservationPort getTempReservationPort;
    private final SaveTempReservationPort saveTempReservationPort;
    private final TempReservationValidation tempReservationValidation;


    public Boolean isExist(TempReservation tempReservation) {
        return getTempReservationPort.get(tempReservation) != null;
    }

    public TempReservation get(TempReservation tempReservation) {
        TempReservation savedTempReservation = getTempReservationPort.get(tempReservation);

        if (savedTempReservation == null) {
            throw new BadRequestException(ARGUMENT_ERROR, ARGUMENT_ERROR.getErrorMessage());
        }

        return savedTempReservation;
    }

    @Transactional
    public void create(TempReservation tempReservation) {
        saveTempReservationPort.save(tempReservationValidation.toValidateModel(tempReservation));
    }
}
