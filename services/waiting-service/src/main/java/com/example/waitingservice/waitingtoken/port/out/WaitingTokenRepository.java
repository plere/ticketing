package com.example.waitingservice.waitingtoken.port.out;

import com.example.waitingservice.waitingtoken.model.WaitingToken;

public interface WaitingTokenRepository {
    WaitingToken save(WaitingToken waitingToken);

    int getMyPosition(WaitingToken waitingToken);
}
