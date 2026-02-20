package com.example.concertservice.concert.adapter.in.web.api.internal;

import com.example.concertservice.concert.adapter.in.web.request.internal.HoldSeatsRequest;
import com.example.concertservice.concert.adapter.in.web.request.internal.ReleaseHoldSeatsRequest;
import com.example.concertservice.concert.application.port.in.usecase.seat.HoldConcertSeatUseCase;
import com.example.httpresponse.response.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.concertservice.concert.adapter.in.web.response.internal.InternalConcertSeatResponseCode.HOLD_SEATS_SUCCESS;
import static com.example.concertservice.concert.adapter.in.web.response.internal.InternalConcertSeatResponseCode.RELEASE_HOLD_SEATS_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/concerts/seats")
public class InternalConcertSeatController {
    private final HoldConcertSeatUseCase holdConcertSeatUseCase;

    @PostMapping("/hold")
    public ResponseEntity<ResponseDto<Void>> holdSeats(@RequestBody @Valid HoldSeatsRequest request) {
        holdConcertSeatUseCase.holdSeats(request.seatIds());

        return ResponseDto.from(HOLD_SEATS_SUCCESS, null);
    }

    @DeleteMapping("/hold")
    public ResponseEntity<ResponseDto<Void>> releaseHoldSeats(@RequestBody @Valid ReleaseHoldSeatsRequest request) {
        holdConcertSeatUseCase.releaseSeats(request.seatIds());

        return ResponseDto.from(RELEASE_HOLD_SEATS_SUCCESS, null);
    }
}
