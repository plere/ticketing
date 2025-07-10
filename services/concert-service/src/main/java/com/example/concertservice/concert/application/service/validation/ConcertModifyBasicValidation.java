package com.example.concertservice.concert.application.service.validation;

import com.example.concertservice.concert.application.port.in.ModifyBasicConcertCommand;
import com.example.concertservice.concert.application.port.out.GetConcertPort;
import com.example.concertservice.concert.domain.Concert;
import com.example.httpresponse.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.concertservice.concert.adapter.in.web.response.AdminConcertErrorResponseCode.MODIFY_CONCERT_STATE_ERROR;

@Component
@RequiredArgsConstructor
public class ConcertModifyBasicValidation {
    private final GetConcertPort getConcertPort;
    private final ConcertValidation concertValidation;

    public Concert validate(ModifyBasicConcertCommand command) {
        Concert concert = getConcertPort.getConcertOrElseThrow(command.id());

        if (!concert.state().isModifiable()) {
            throw new BadRequestException(MODIFY_CONCERT_STATE_ERROR, MODIFY_CONCERT_STATE_ERROR.getErrorMessage());
        }

        Concert validConcert = command.toModel(concert);

        concertValidation.checkRounds(validConcert);
        concertValidation.checkTicketingTime(validConcert);

        return validConcert;
    }
}
