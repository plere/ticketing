package com.example.concertservice.concert.adapter.in.web.api.internal;

import com.example.concertservice.concert.application.port.in.usecase.ConcertGetUseCase;
import com.example.concertservice.concert.domain.Concert;
import com.example.httpresponse.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.concertservice.concert.adapter.in.web.response.internal.InternalConcertResponseCode.INTERNAL_GET_CONCERT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/concerts")
public class InternalConcertController {
    private final ConcertGetUseCase concertGetUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Concert>> get(@PathVariable Long id) {
        return ResponseDto.from(INTERNAL_GET_CONCERT,
            concertGetUseCase.getById(id));
    }
}
