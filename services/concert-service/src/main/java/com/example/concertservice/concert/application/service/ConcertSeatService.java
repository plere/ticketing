package com.example.concertservice.concert.application.service;

import com.example.concertservice.concert.application.port.in.usecase.seat.HoldConcertSeatUseCase;
import com.example.concertservice.concert.application.port.out.seat.HoldConcertSeatPort;
import com.example.concertservice.concert.application.service.exception.seat.ConcertSeatLockingFailureException;
import com.example.concertservice.concert.application.service.validation.seat.ConcertSeatValidation;
import com.example.concertservice.concert.domain.ConcertSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConcertSeatService implements HoldConcertSeatUseCase {
    private final HoldConcertSeatPort holdConcertSeatPort;
    private final ConcertSeatValidation concertSeatValidation;

    @Override
    @Transactional(timeout = 3)
    public void holdSeats(Set<Long> seatIds) {
        try {
            List<ConcertSeat> concertSeats = holdConcertSeatPort.findAll(seatIds);
            concertSeatValidation.validateSeatStateToHold(concertSeats);
            holdConcertSeatPort.holdSeats(seatIds);
        } catch (QueryTimeoutException | PessimisticLockingFailureException e) {
            throw new ConcertSeatLockingFailureException();
        }
    }
}
