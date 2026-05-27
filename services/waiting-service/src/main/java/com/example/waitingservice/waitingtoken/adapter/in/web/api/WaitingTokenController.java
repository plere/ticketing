package com.example.waitingservice.waitingtoken.adapter.in.web.api;

import com.example.httpresponse.response.ResponseDto;
import com.example.waitingservice.waitingtoken.adapter.in.web.response.WaitingTokenResponseCode;
import com.example.waitingservice.waitingtoken.application.service.WaitingTokenService;
import com.example.waitingservice.waitingtoken.model.WaitingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/waiting-token")
public class WaitingTokenController {
    private final WaitingTokenService waitingTokenService;

    @GetMapping("/concerts/{concertId}/rounds/{roundId}")
    public ResponseEntity<ResponseDto<WaitingToken>> get(@PathVariable Long concertId, @PathVariable Long roundId) {
        return ResponseDto.from(HttpStatus.CREATED.value(), WaitingTokenResponseCode.CREATED_WAITING_TOKEN, waitingTokenService.create(concertId, roundId));
    }

    @GetMapping("/position/concerts/{id}/{token}")
    public ResponseEntity<ResponseDto<Integer>> get(@PathVariable String id, @PathVariable String token) {
        return ResponseDto.from(
            WaitingTokenResponseCode.GET_MY_WAITING_POSITION,
            waitingTokenService.getWaitingPosition(
                WaitingToken.builder()
                    .id(id)
                    .token(token)
                    .build()
            )
        );
    }
}
