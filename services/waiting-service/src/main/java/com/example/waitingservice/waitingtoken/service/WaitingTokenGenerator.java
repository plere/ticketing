package com.example.waitingservice.waitingtoken.service;

import com.example.waitingservice.waitingtoken.model.WaitingToken;

public interface WaitingTokenGenerator {
    WaitingToken generate(long id);
}
