package com.example.concertservice.concert.application.service.validation;

import com.example.concertservice.concert.application.port.in.ModifyConcertPlaceCommand;
import com.example.concertservice.concert.application.port.out.GetConcertPort;
import com.example.concertservice.concert.domain.Concert;
import com.example.concertservice.place.application.port.out.GetAllPlaceSeatsPort;
import com.example.httpresponse.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.concertservice.concert.adapter.in.web.response.AdminConcertErrorResponseCode.MODIFY_CONCERT_STATE_ERROR;

@Component
@RequiredArgsConstructor
public class ConcertModifyPlaceValidation {
    private final ConcertValidation concertValidation;
    private final GetConcertPort getConcertPort;
    private final GetAllPlaceSeatsPort getAllPlaceSeatsPort;

    public Concert validate(ModifyConcertPlaceCommand command) {
        Concert concert = getConcertPort.getConcertOrElseThrow(command.id());

        if (!concert.state().isModifiable()) {
            throw new BadRequestException(MODIFY_CONCERT_STATE_ERROR, MODIFY_CONCERT_STATE_ERROR.getErrorMessage());
        }

        Concert validConcert = command.toModel(concert, getAllPlaceSeatsPort.getAllByPlaceId(concert.placeId()));
        concertValidation.checkAllPlaceSeat(validConcert);
        concertValidation.checkSeatGrade(validConcert);

        return validConcert;
    }
}
