package com.example.waitingservice.waitingtoken.model;

import lombok.Builder;

@Builder
public record WaitingToken(
    String id,
    String token
) {
}
