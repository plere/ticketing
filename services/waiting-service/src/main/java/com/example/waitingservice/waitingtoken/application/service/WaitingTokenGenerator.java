package com.example.waitingservice.waitingtoken.application.service;

import com.example.waitingservice.waitingtoken.model.WaitingToken;

public interface WaitingTokenGenerator {
    WaitingToken generate(long id);
}
