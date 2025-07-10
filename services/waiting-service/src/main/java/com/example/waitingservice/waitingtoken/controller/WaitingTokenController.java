package com.example.waitingservice.waitingtoken.controller;

import com.example.httpresponse.response.ResponseDto;
import com.example.waitingservice.waitingtoken.controller.response.WaitingTokenResponseCode;
import com.example.waitingservice.waitingtoken.model.WaitingToken;
import com.example.waitingservice.waitingtoken.service.WaitingTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/waiting-token")
public class WaitingTokenController {
    private final WaitingTokenService waitingTokenService;


    @GetMapping("/concerts/{id}")
    public ResponseDto<WaitingToken> get(@PathVariable Long id) {
        //Todo
        //사용자 확인은 나중에 구현
        return ResponseDto.from(HttpStatus.CREATED.value(), WaitingTokenResponseCode.CREATED_WAITING_TOKEN, waitingTokenService.create(id));
    }

    @GetMapping("/concerts/{id}/position/{token}")
    public ResponseDto<Integer> get(@PathVariable Long id, @PathVariable String token) {
        //Todo
        //사용자 확인은 나중에 구현
        WaitingToken waitingToken = WaitingToken.builder()
            .id(id)
            .token(token)
            .build();

        return ResponseDto.from(WaitingTokenResponseCode.GET_MY_WAITING_POSITION, waitingTokenService.getWaitingPosition(waitingToken));
    }
}
