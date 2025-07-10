package com.example.concertservice.concert.adapter.out.persistence;

import com.example.concertservice.concert.adapter.out.persistence.repository.ConcertRepository;
import com.example.concertservice.concert.application.port.out.ChangeStateConcertPort;
import com.example.concertservice.concert.application.port.out.GetConcertPort;
import com.example.concertservice.concert.application.port.out.SaveConcertPort;
import com.example.concertservice.concert.application.port.out.UpdateConcertPort;
import com.example.concertservice.concert.domain.Concert;
import com.example.concertservice.concert.domain.ConcertState;
import com.example.httpresponse.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.httpresponse.exception.CommonErrorCode.NO_CONTENT;

@Component
@RequiredArgsConstructor
public class ConcertPersistenceAdapter implements SaveConcertPort, ChangeStateConcertPort, GetConcertPort, UpdateConcertPort {
    private final ConcertRepository concertRepository;

    @Override
    public Concert save(Concert concert) {
        return concertRepository.save(concert);
    }


    @Override
    public void changeStateToClose(long id) {
        concertRepository.updateState(getConcertOrElseThrow(id), ConcertState.CLOSE);
    }

    @Override
    public void changeStateToOpen(long id) {
        concertRepository.updateState(getConcertOrElseThrow(id), ConcertState.OPEN);
    }

    @Override
    public Concert getConcertOrElseThrow(long id) {
        return concertRepository.getConcert(id).orElseThrow(() -> new BadRequestException(NO_CONTENT, NO_CONTENT.getErrorMessage()));
    }

    @Override
    public List<Concert> getAllTodoChangeStateToOpen() {
        return concertRepository.getAllTodoChangeStateToOpen();
    }

    @Override
    public void update(Concert concert) {
        concertRepository.update(concert);
    }
}
