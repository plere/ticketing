package com.example.admin.concert.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConcertState {
    READY(true),
    OPEN(false),
    CLOSE(false);

    private final boolean modifiable;
}
