package com.example.waitingservice.reservationtoken.application.port.out;

import com.example.waitingservice.waitingtoken.model.WaitingToken;

public interface GetWaitingTokenPort {
    boolean isTokenTurn(WaitingToken waitingToken);
}
