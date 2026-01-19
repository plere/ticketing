package com.example.concertservice.concert.adapter.in.web.api;

import com.example.concertservice.concert.adapter.in.web.request.GetAllConcertRequest;
import com.example.concertservice.concert.adapter.in.web.response.ConcertGetAllByPageableResponse;
import com.example.concertservice.concert.application.port.in.usecase.ConcertGetAllUseCase;
import com.example.httpresponse.pageable.PageableResponse;
import com.example.httpresponse.response.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.concertservice.concert.adapter.in.web.response.UserConcertResponseCode.GET_ALL_CONCERT_BY_PAGEABLE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/concerts")
public class UserConcertController {
    private final ConcertGetAllUseCase concertGetAllUseCase;

    @GetMapping
    public ResponseEntity<ResponseDto<PageableResponse<ConcertGetAllByPageableResponse>>> create(@ModelAttribute @Valid GetAllConcertRequest request) {
        return ResponseDto.from(GET_ALL_CONCERT_BY_PAGEABLE,
            concertGetAllUseCase.getAllByPageable(request.pageable(), request.name())
                .map(ConcertGetAllByPageableResponse::from));
    }
}
