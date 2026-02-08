package com.example.concertservice.concert.adapter.in.web.api.user;

import com.example.concertservice.concert.adapter.in.web.response.user.UserGetAllConcertSeatCountResponse;
import com.example.concertservice.concert.adapter.in.web.response.user.UserGetAllEmptyConcertSeatCountResponse;
import com.example.concertservice.concert.application.port.in.usecase.round.ConcertRoundGetUseCase;
import com.example.httpresponse.response.ResponseDto;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.concertservice.concert.adapter.in.web.response.user.UserConcertRoundResponseCode.GET_EMPTY_SEAT_BY_ROUND_ID;
import static com.example.concertservice.concert.adapter.in.web.response.user.UserConcertRoundResponseCode.GET_SEATS_BY_ROUND_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/concerts/rounds")
public class UserConcertRoundController {
    private final ConcertRoundGetUseCase concertRoundGetUseCase;

    @GetMapping("/{id}/seats")
    public ResponseEntity<ResponseDto<UserGetAllConcertSeatCountResponse>> getAllSeats(@PathVariable @Positive Long id) {
        return ResponseDto.from(GET_SEATS_BY_ROUND_ID,
            UserGetAllConcertSeatCountResponse.from(concertRoundGetUseCase.getAllConcertSeatByRoundId(id)));
    }

    @GetMapping("/{id}/seats/empty")
    public ResponseEntity<ResponseDto<UserGetAllEmptyConcertSeatCountResponse>> getEmptySeat(@PathVariable @Positive Long id) {
        return ResponseDto.from(GET_EMPTY_SEAT_BY_ROUND_ID,
            UserGetAllEmptyConcertSeatCountResponse.from(concertRoundGetUseCase.getAllEmptyConcertSeatByRoundId(id)));
    }
}
