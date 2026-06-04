package com.example.waitingservice.waitingtoken.adapter.in.web.api;

import com.example.httpresponse.response.ResponseDto;
import com.example.waitingservice.waitingtoken.adapter.in.web.response.WaitingTokenResponseCode;
import com.example.waitingservice.waitingtoken.application.service.WaitingTokenService;
import com.example.waitingservice.waitingtoken.model.WaitingToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "waiting-token")
@RestController
@RequiredArgsConstructor
@RequestMapping("/waiting-token")
public class WaitingTokenController {
    private final WaitingTokenService waitingTokenService;

    @GetMapping("/concerts/{concertId}/rounds/{roundId}")
    @Operation(summary = "특정 콘서트 회차에 대해 웨이팅 토큰 얻는 API")
    public ResponseEntity<ResponseDto<WaitingToken>> get(@PathVariable Long concertId, @PathVariable Long roundId) {
        return ResponseDto.from(HttpStatus.CREATED.value(), WaitingTokenResponseCode.CREATED_WAITING_TOKEN, waitingTokenService.create(concertId, roundId));
    }

    @GetMapping("/position/concerts")
    @Operation(summary = "웨이팅 토큰의 현재 대기열 위치 조회 API")
    public ResponseEntity<ResponseDto<Integer>> get(@ModelAttribute @Valid WaitingToken waitingToken) {
        return ResponseDto.from(
            WaitingTokenResponseCode.GET_MY_WAITING_POSITION,
            waitingTokenService.getWaitingPosition(waitingToken)
        );
    }
}
