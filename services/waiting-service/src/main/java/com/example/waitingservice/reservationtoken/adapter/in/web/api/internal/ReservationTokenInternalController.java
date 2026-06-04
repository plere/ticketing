package com.example.waitingservice.reservationtoken.adapter.in.web.api.internal;

import com.example.httpresponse.response.ResponseDto;
import com.example.waitingservice.reservationtoken.adapter.in.web.response.ReservationTokenResponseCode;
import com.example.waitingservice.reservationtoken.application.service.ReservationTokenService;
import com.example.waitingservice.reservationtoken.model.ReservationToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "reservation-token/internal")
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/reservation-token")
public class ReservationTokenInternalController {
    private final ReservationTokenService reservationTokenService;

    @GetMapping("/concerts/validation")
    @Operation(summary = "예매 토큰이 유효성 확인 API")
    public ResponseEntity<ResponseDto<Boolean>> isValid(@ModelAttribute @Valid ReservationToken token) {
        return ResponseDto.from(ReservationTokenResponseCode.CHECK_RESERVATION_TOKEN_VALIDATION,
            reservationTokenService.isValid(token)
        );
    }
}
