package com.example.waitingservice.reservationtoken.adapter.in.web.api;

import com.example.httpresponse.response.ResponseDto;
import com.example.waitingservice.reservationtoken.adapter.in.web.response.ReservationTokenResponseCode;
import com.example.waitingservice.reservationtoken.application.service.ReservationTokenService;
import com.example.waitingservice.reservationtoken.model.ReservationToken;
import com.example.waitingservice.waitingtoken.model.WaitingToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "reservation-token")
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation-token")
public class ReservationTokenController {
    private final ReservationTokenService reservationTokenService;

    @GetMapping("/concerts/{id}/{token}")
    @Operation(summary = "웨이팅 토큰으로 예매 토큰 얻는 API")
    public ResponseEntity<ResponseDto<ReservationToken>> get(@PathVariable String id, @PathVariable String token) {
        return ResponseDto.from(HttpStatus.CREATED.value(), ReservationTokenResponseCode.CREATED_RESERVATION_TOKEN,
            reservationTokenService.create(
                WaitingToken.builder()
                    .id(id)
                    .token(token)
                    .build()
            )
        );
    }
}
