package com.example.waitingservice.waitingtoken.model;

import lombok.Builder;

@Builder
public record WaitingToken(
    long id,
    String token
) {
}
