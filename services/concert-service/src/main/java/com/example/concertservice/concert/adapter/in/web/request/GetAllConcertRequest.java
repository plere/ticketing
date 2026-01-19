package com.example.concertservice.concert.adapter.in.web.request;

import com.example.httpresponse.pageable.PageableRequest;

public record GetAllConcertRequest(
    PageableRequest pageable,
    String name
) {
}
