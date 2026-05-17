package com.example.concertservice.concert.adapter.in.web.api.internal;

import com.example.concertservice.concert.adapter.in.web.request.internal.HoldSeatsRequest;
import com.example.concertservice.concert.adapter.in.web.request.internal.ReleaseHoldSeatsRequest;
import com.example.concertservice.concert.application.port.in.usecase.seat.HoldConcertSeatUseCase;
import com.example.httpresponse.response.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.concertservice.concert.adapter.in.web.response.internal.InternalConcertSeatResponseCode.HOLD_SEATS_SUCCESS;
import static com.example.concertservice.concert.adapter.in.web.response.internal.InternalConcertSeatResponseCode.RELEASE_HOLD_SEATS_SUCCESS;

@Tag(name = "Concert/Internal")
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/concerts/seats")
public class InternalConcertSeatController {
    private final HoldConcertSeatUseCase holdConcertSeatUseCase;

    @PostMapping("/hold")
    @Operation(summary = "좌석 선점하기(상태를 hold로 변경)")
    public ResponseEntity<ResponseDto<Void>> holdSeats(@RequestBody @Valid HoldSeatsRequest request) {
        holdConcertSeatUseCase.holdSeats(request.seatIds());

        return ResponseDto.from(HOLD_SEATS_SUCCESS, null);
    }

    @DeleteMapping("/hold")
    @Operation(summary = "좌석 선점 해제(상태를 empty로 변경)")
    public ResponseEntity<ResponseDto<Void>> releaseHoldSeats(@RequestBody @Valid ReleaseHoldSeatsRequest request) {
        holdConcertSeatUseCase.releaseSeats(request.seatIds());

        return ResponseDto.from(RELEASE_HOLD_SEATS_SUCCESS, null);
    }
}
