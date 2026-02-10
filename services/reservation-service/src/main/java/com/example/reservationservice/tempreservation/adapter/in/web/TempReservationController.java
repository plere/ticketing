package com.example.reservationservice.tempreservation.adapter.in.web;

import com.example.httpresponse.response.ResponseDto;
import com.example.reservationservice.tempreservation.model.TempReservation;
import com.example.reservationservice.tempreservation.service.TempReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.reservationservice.tempreservation.adapter.in.TempReservationResponseCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation/temp")
public class TempReservationController {
    private final TempReservationService tempReservationService;

    // Todo) check reservation token
    //change) userId -> userEmail
    //userId를 삭제하고 Token에서 얻어오기
    //내부적으로 email -> id 변경해서 조회
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

    // Todo) check reservation token
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

    // Todo) check reservation token
    //change) userId -> userEmail
    //내부적으로 email -> id 변경해서 조회
    @PostMapping("/concerts")
    public ResponseDto<Void> create(@RequestBody @Valid TempReservation tempReservation) {
        tempReservationService.create(tempReservation);

        return ResponseDto.from(CREATED_TEMP_RESERVATION, null);
    }
}
