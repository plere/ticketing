package com.example.concertservice.concert.application.service;

import com.example.concertservice.concert.application.port.out.ChangeStateConcertPort;
import com.example.concertservice.concert.application.port.out.GetConcertPort;
import com.example.concertservice.concert.domain.Concert;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConcertUpdateStateScheduler {
    private final GetConcertPort getConcertPort;
    private final ChangeStateConcertPort changeStateConcertPort;

    @Scheduled(
        cron = "0 0,30 * * * *"
    )
    @Transactional
    public void toOpenExecutor() {
        List<Concert> concerts = getConcertPort.getAllTodoChangeStateToOpen();
        concerts.forEach(concert ->
            changeStateConcertPort.changeStateToOpen(concert.id())
        );
    }
}
