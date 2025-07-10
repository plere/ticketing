package com.example.concertservice.concert.application.service;

import com.example.concertservice.concert.application.port.in.ModifyBasicConcertCommand;
import com.example.concertservice.concert.application.port.in.ModifyConcertPlaceCommand;
import com.example.concertservice.concert.application.port.in.usecase.ConcertCreateUseCase;
import com.example.concertservice.concert.application.port.in.usecase.ConcertModifyBasicInfoUseCase;
import com.example.concertservice.concert.application.port.in.usecase.ConcertModifyPlaceInfoUseCase;
import com.example.concertservice.concert.application.port.in.usecase.ConcertStateToCloseUseCase;
import com.example.concertservice.concert.application.port.out.ChangeStateConcertPort;
import com.example.concertservice.concert.application.port.out.SaveConcertPort;
import com.example.concertservice.concert.application.port.out.UpdateConcertPort;
import com.example.concertservice.concert.application.service.validation.ConcertCreateValidation;
import com.example.concertservice.concert.application.service.validation.ConcertModifyBasicValidation;
import com.example.concertservice.concert.application.service.validation.ConcertModifyPlaceValidation;
import com.example.concertservice.concert.domain.Concert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConcertService implements ConcertCreateUseCase, ConcertStateToCloseUseCase, ConcertModifyBasicInfoUseCase, ConcertModifyPlaceInfoUseCase {
    private final ConcertCreateValidation createValidate;
    private final ConcertModifyBasicValidation modifyBasicValidation;
    private final ConcertModifyPlaceValidation modifyPlaceValidation;
    
    private final SaveConcertPort saveConcertPort;
    private final ChangeStateConcertPort changeStateConcertPort;
    private final UpdateConcertPort updateConcertPort;

    @Override
    @Transactional
    public long create(Concert concert) {
        Concert validateConcert = createValidate.toValidateConcert(concert);

        return saveConcertPort.save(validateConcert).id();
    }

    @Override
    @Transactional
    public void changeStateToClose(long id) {
        changeStateConcertPort.changeStateToClose(id);
    }

    @Override
    @Transactional
    public void modifyBasicInfo(ModifyBasicConcertCommand command) {
        Concert concert = modifyBasicValidation.validate(command);

        updateConcertPort.update(concert);
    }

    @Override
    @Transactional
    public void modifyPlaceInfo(ModifyConcertPlaceCommand command) {
        Concert concert = modifyPlaceValidation.validate(command);

        updateConcertPort.update(concert);
    }
}
