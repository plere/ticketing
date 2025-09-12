package com.example.reservationservice.tempreservation.service;

import com.example.httpresponse.exception.BadRequestException;
import com.example.reservationservice.tempreservation.model.TempReservation;
import com.example.reservationservice.tempreservation.port.out.ConcertSeatStatePort;
import com.example.reservationservice.tempreservation.port.out.GetTempReservationPort;
import com.example.reservationservice.tempreservation.port.out.SaveTempReservationPort;
import com.example.reservationservice.tempreservation.service.validation.TempReservationValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static com.example.httpresponse.exception.CommonErrorCode.ARGUMENT_ERROR;
import static com.example.reservationservice.tempreservation.controller.TempReservationErrorCode.SEATS_IS_ALREADY_RESERVED;

@Component
@RequiredArgsConstructor
public class TempReservationService {
    private final GetTempReservationPort getTempReservationPort;
    private final SaveTempReservationPort saveTempReservationPort;
    private final TempReservationValidation tempReservationValidation;
    private final ConcertSeatStatePort concertSeatStatePort;


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
        if (!concertSeatStatePort.isEmpty(tempReservation.seats().stream().map(TempReservation.TempReservationSeat::seatId).collect(Collectors.toSet()))) {
            throw new BadRequestException(SEATS_IS_ALREADY_RESERVED, SEATS_IS_ALREADY_RESERVED.getErrorMessage());
        }

        if (!saveTempReservationPort.save(tempReservationValidation.toValidateModel(tempReservation))) {
            throw new BadRequestException(SEATS_IS_ALREADY_RESERVED, SEATS_IS_ALREADY_RESERVED.getErrorMessage());
        }
    }
}
