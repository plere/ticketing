package com.example.waitingservice.waitingtoken.service;

import com.example.waitingservice.waitingtoken.model.WaitingToken;
import com.example.waitingservice.waitingtoken.port.out.GetReservationPort;
import com.example.waitingservice.waitingtoken.port.out.WaitingTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WaitingTokenService {
    private final WaitingTokenRepository waitingTokenRepository;
    private final WaitingTokenGenerator waitingTokenGenerator;
    private final GetReservationPort getReservationPort;


    @Transactional
    public WaitingToken create(long id) {
        return waitingTokenRepository.save(waitingTokenGenerator.generate(id));
    }

    public int getWaitingPosition(WaitingToken waitingToken) {
        int myPosition = waitingTokenRepository.getMyPosition(waitingToken);
        int availableCount = getReservationPort.availableProcessingCount(waitingToken.id());

        return Math.min(0, myPosition - availableCount);
    }
}
