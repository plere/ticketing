package com.example.concertservice.concert.application.port.out;

import com.example.concertservice.concert.domain.Concert;
import com.example.httpresponse.pageable.PageableRequest;
import com.example.httpresponse.pageable.PageableResponse;

import java.util.List;

public interface GetConcertPort {
    Concert getConcertOrElseThrow(long id);

    List<Concert> getAllTodoChangeStateToOpen();

    PageableResponse<Concert> getAllByPageable(PageableRequest page, String name);
}
