package com.example.concertservice.concert.adapter.in.web.api.internal;

import com.example.concertservice.concert.adapter.in.web.request.internal.HoldSeatsRequest;
import com.example.concertservice.concert.application.port.in.usecase.seat.HoldConcertSeatUseCase;
import com.example.httpresponse.response.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.concertservice.concert.adapter.in.web.response.internal.InternalConcertSeatResponseCode.HOLD_SEATS_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/concerts/seats")
public class InternalConcertSeatController {
    private final HoldConcertSeatUseCase holdConcertSeatUseCase;

    @PostMapping
    public ResponseEntity<ResponseDto<Void>> holdSeats(@RequestBody @Valid HoldSeatsRequest request) {
        holdConcertSeatUseCase.holdSeats(request.seatIds());

        return ResponseDto.from(HOLD_SEATS_SUCCESS, null);
    }
}
