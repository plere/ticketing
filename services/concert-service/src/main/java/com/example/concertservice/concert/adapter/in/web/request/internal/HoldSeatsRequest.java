package com.example.concertservice.concert.adapter.in.web.request.internal;

import java.util.Set;

public record HoldSeatsRequest(
    Set<Long> seatIds
) {
}
