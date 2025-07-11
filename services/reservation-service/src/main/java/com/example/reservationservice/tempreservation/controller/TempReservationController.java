package com.example.reservationservice.tempreservation.controller;

import com.example.httpresponse.response.ResponseDto;
import com.example.reservationservice.tempreservation.model.TempReservation;
import com.example.reservationservice.tempreservation.service.TempReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.reservationservice.tempreservation.controller.TempReservationResponseCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation/temp")
public class TempReservationController {
    private final TempReservationService tempReservationService;

    // check reservation token
    @GetMapping("/concerts/{id}/exist/{userId}")
    public ResponseDto<Boolean> isExist(@PathVariable Long id, @PathVariable Long userId) {
        //Todo
        //사용자 확인은 나중에 구현
        return ResponseDto.from(IS_EXIST_TEMP_RESERVATION,
            tempReservationService.isExist(TempReservation.builder()
                .userId(userId)
                .concertId(id)
                .build())
        );
    }

    // check reservation token
    @GetMapping("/concerts/{id}/rounds/{round_id}/{userId}")
    public ResponseDto<TempReservation> get(@PathVariable Long id, @PathVariable Long round_id, @PathVariable Long userId) {
        //Todo
        //사용자 확인은 나중에 구현
        return ResponseDto.from(GET_TEMP_RESERVATION,
            tempReservationService.get(TempReservation.builder()
                .userId(userId)
                .concertId(id)
                .roundId(round_id)
                .build())
        );
    }

    @PostMapping("/concerts")
    public ResponseDto<Void> create(@RequestBody @Valid TempReservation tempReservation) {
        //Todo
        //사용자 확인은 나중에 구현
        tempReservationService.create(tempReservation);

        return ResponseDto.from(CREATED_TEMP_RESERVATION, null);
    }
}
