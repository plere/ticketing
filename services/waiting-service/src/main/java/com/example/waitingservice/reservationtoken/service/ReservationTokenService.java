package com.example.waitingservice.reservationtoken.service;

import com.example.httpresponse.exception.BadRequestException;
import com.example.waitingservice.reservationtoken.model.ReservationToken;
import com.example.waitingservice.reservationtoken.port.out.CreateReservationTokenPort;
import com.example.waitingservice.reservationtoken.port.out.GetWaitingTokenPort;
import com.example.waitingservice.waitingtoken.model.WaitingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.httpresponse.exception.CommonErrorCode.ARGUMENT_ERROR;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationTokenService {
    private final CreateReservationTokenPort createReservationTokenPort;
    private final ReservationTokenGenerator reservationTokenGenerator;
    private final GetWaitingTokenPort getWaitingTokenPort;

    public ReservationToken create(WaitingToken waitingToken) {
        if (!getWaitingTokenPort.isTokenTurn(waitingToken)) {
            throw new BadRequestException(ARGUMENT_ERROR, ARGUMENT_ERROR.getErrorMessage());
        }

        return createReservationTokenPort.create(reservationTokenGenerator.generate(waitingToken));
    }
}
