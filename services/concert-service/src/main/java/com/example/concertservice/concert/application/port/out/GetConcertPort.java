package com.example.concertservice.concert.application.port.out;

import com.example.concertservice.concert.domain.Concert;

import java.util.List;

public interface GetConcertPort {
    Concert getConcertOrElseThrow(long id);

    List<Concert> getAllTodoChangeStateToOpen();
}
