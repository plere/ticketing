package com.example.concertservice.concert.adapter.out.persistence.repository;

import com.example.concertservice.concert.domain.Concert;
import com.example.concertservice.concert.domain.ConcertState;
import com.example.httpresponse.pageable.PageableRequest;
import com.example.httpresponse.pageable.PageableResponse;

import java.util.List;
import java.util.Optional;

public interface ConcertRepository {
    Concert save(Concert concert);

    Optional<Concert> getConcert(long id);

    void updateState(Concert concert, ConcertState state);

    void update(Concert concert);

    List<Concert> getAllTodoChangeStateToOpen();

    PageableResponse<Concert> getAllByPageable(PageableRequest page);
}
