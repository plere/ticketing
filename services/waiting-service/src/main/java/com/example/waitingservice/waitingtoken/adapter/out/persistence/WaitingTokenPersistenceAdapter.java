package com.example.waitingservice.waitingtoken.adapter.out.persistence;

import com.example.httpresponse.exception.BadRequestException;
import com.example.waitingservice.waitingtoken.application.port.out.WaitingTokenRepository;
import com.example.waitingservice.waitingtoken.model.WaitingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.example.httpresponse.exception.CommonErrorCode.ARGUMENT_ERROR;

@Component
@RequiredArgsConstructor
public class WaitingTokenPersistenceAdapter implements WaitingTokenRepository {
    private static final Duration DEFAULT_DURATION = Duration.ofSeconds(30);

    private final PositionKeyManager positionKeyManager;


    @Override
    public WaitingToken save(WaitingToken waitingToken) {
        positionKeyManager.deleteDummyWaitingToken(waitingToken);
        positionKeyManager.savePositionKey(waitingToken, DEFAULT_DURATION);

        return waitingToken;
    }

    @Override
    public int getMyPosition(WaitingToken waitingToken) {
        positionKeyManager.deleteDummyWaitingToken(waitingToken);
        int rank = positionKeyManager.getPosition(waitingToken, DEFAULT_DURATION);

        if (rank == -1) {
            throw new BadRequestException(ARGUMENT_ERROR, ARGUMENT_ERROR.getErrorMessage());
        }

        return rank;
    }
}
