package com.example.concertservice.concert.application.port.in.usecase;

import com.example.concertservice.concert.domain.Concert;
import com.example.httpresponse.pageable.PageableRequest;
import com.example.httpresponse.pageable.PageableResponse;

public interface ConcertGetAllUseCase {
    PageableResponse<Concert> getAllByPageable(PageableRequest page, String name);
}
