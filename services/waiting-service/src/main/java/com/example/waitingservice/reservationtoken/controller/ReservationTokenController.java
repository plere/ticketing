package com.example.waitingservice.reservationtoken.controller;

import com.example.httpresponse.response.ResponseDto;
import com.example.waitingservice.reservationtoken.controller.response.ReservationTokenResponseCode;
import com.example.waitingservice.reservationtoken.model.ReservationToken;
import com.example.waitingservice.reservationtoken.service.ReservationTokenService;
import com.example.waitingservice.waitingtoken.model.WaitingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation-token")
public class ReservationTokenController {
    private final ReservationTokenService reservationTokenService;

    @GetMapping("/concerts/{id}/{token}")
    public ResponseDto<ReservationToken> get(@PathVariable Long id, @PathVariable String token) {
        //Todo
        //사용자 확인은 나중에 구현
        return ResponseDto.from(HttpStatus.CREATED.value(), ReservationTokenResponseCode.CREATED_RESERVATION_TOKEN,
            reservationTokenService.create(WaitingToken.builder()
                .id(id)
                .token(token)
                .build())
        );
    }
}
