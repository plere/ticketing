package com.example.concertservice.concert.adapter.in.web.api;

import com.example.concertservice.concert.adapter.in.web.request.GetAllConcertRequest;
import com.example.concertservice.concert.adapter.in.web.response.ConcertGetAllByPageableResponse;
import com.example.concertservice.concert.adapter.in.web.response.GetConcertResponse;
import com.example.concertservice.concert.application.port.in.usecase.ConcertGetUseCase;
import com.example.httpresponse.pageable.PageableResponse;
import com.example.httpresponse.response.ResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.concertservice.concert.adapter.in.web.response.UserConcertResponseCode.GET_ALL_CONCERT_BY_PAGEABLE;
import static com.example.concertservice.concert.adapter.in.web.response.UserConcertResponseCode.GET_CONCERT_BY_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/concerts")
public class UserConcertController {
    private final ConcertGetUseCase concertGetUseCase;

    @GetMapping
    public ResponseEntity<ResponseDto<PageableResponse<ConcertGetAllByPageableResponse>>> getAll(@ModelAttribute @Valid GetAllConcertRequest request) {
        return ResponseDto.from(GET_ALL_CONCERT_BY_PAGEABLE,
            concertGetUseCase.getAllByPageable(request.pageable(), request.name())
                .map(ConcertGetAllByPageableResponse::from));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<GetConcertResponse>> get(@PathVariable @Positive Long id) {
        return ResponseDto.from(GET_CONCERT_BY_ID,
            GetConcertResponse.from(concertGetUseCase.getById(id)));
    }
}
