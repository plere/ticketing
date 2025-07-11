package com.example.reservationservice.tempreservation.controller;

import com.example.httpresponse.response.ResponseDto;
import com.example.reservationservice.tempreservation.model.TempReservation;
import com.example.reservationservice.tempreservation.service.TempReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.reservationservice.tempreservation.controller.TempReservationResponseCode.IS_EXIST_TEMP_RESERVATION;

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
}
