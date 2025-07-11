package com.example.waitingservice.reservationtoken.adapter.out.internal;

import com.example.waitingservice.reservationtoken.application.port.out.GetWaitingTokenPort;
import com.example.waitingservice.waitingtoken.application.service.WaitingTokenService;
import com.example.waitingservice.waitingtoken.model.WaitingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WaitingTokenInternalAdapter implements GetWaitingTokenPort {
    private final WaitingTokenService waitingTokenService;

    @Override
    public boolean isTokenTurn(WaitingToken waitingToken) {
        return waitingTokenService.isTokenTurn(waitingToken);
    }
}
