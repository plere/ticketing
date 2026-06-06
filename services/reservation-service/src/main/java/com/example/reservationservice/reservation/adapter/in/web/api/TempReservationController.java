package com.example.reservationservice.reservation.adapter.in.web.api;

import com.example.checkauth.UserToken;
import com.example.chekcreservationtoken.aop.RequiredReservationToken;
import com.example.httpresponse.response.ResponseDto;
import com.example.reservationservice.reservation.adapter.in.web.request.CreateTempReservationRequest;
import com.example.reservationservice.reservation.application.service.TempReservationService;
import com.example.reservationservice.reservation.domain.TempReservation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.reservationservice.reservation.adapter.in.web.response.TempReservationResponseCode.CREATED_TEMP_RESERVATION;
import static com.example.reservationservice.reservation.adapter.in.web.response.TempReservationResponseCode.GET_TEMP_RESERVATION;

@Tag(name = "TempReservation")
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation/temp")
public class TempReservationController {
    private final TempReservationService tempReservationService;

    @GetMapping("/concerts/{id}/rounds/{round_id}")
    @RequiredReservationToken
    @Operation(summary = "저장된 임시 예매정보 조회")
    public ResponseEntity<ResponseDto<TempReservation>> get(@PathVariable Long id, @PathVariable Long round_id, UserToken userToken) {
        return ResponseDto.from(GET_TEMP_RESERVATION,
            tempReservationService.get(TempReservation.builder()
                .userId(userToken.getId())
                .concertId(id)
                .roundId(round_id)
                .build())
        );
    }

    @PostMapping("/concerts")
    @Operation(summary = "임시 예매 정보 생성")
    public ResponseEntity<ResponseDto<Void>> create(@RequestBody @Valid CreateTempReservationRequest request, UserToken userToken) {
        tempReservationService.createAndHoldSeats(request.toModel(userToken));

        return ResponseDto.from(CREATED_TEMP_RESERVATION, null);
    }
}
