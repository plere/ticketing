package com.example.concertservice.concert.adapter.in.web.api.user;

import com.example.concertservice.concert.adapter.in.web.request.GetAllConcertRequest;
import com.example.concertservice.concert.adapter.in.web.response.ConcertGetAllByPageableResponse;
import com.example.concertservice.concert.adapter.in.web.response.GetConcertResponse;
import com.example.concertservice.concert.application.port.in.usecase.ConcertGetUseCase;
import com.example.httpresponse.pageable.PageableResponse;
import com.example.httpresponse.response.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.concertservice.concert.adapter.in.web.response.user.UserConcertResponseCode.GET_ALL_CONCERT_BY_PAGEABLE;
import static com.example.concertservice.concert.adapter.in.web.response.user.UserConcertResponseCode.GET_CONCERT_BY_ID;

@Tag(name = "Concert/User")
@RestController
@RequiredArgsConstructor
@RequestMapping("/concerts")
public class UserConcertController {
    private final ConcertGetUseCase concertGetUseCase;

    @GetMapping
    @Operation(summary = "모든 콘서트 조회")
    public ResponseEntity<ResponseDto<PageableResponse<ConcertGetAllByPageableResponse>>> getAll(@ModelAttribute @Valid GetAllConcertRequest request) {
        return ResponseDto.from(GET_ALL_CONCERT_BY_PAGEABLE,
            concertGetUseCase.getAllByPageable(request.pageable(), request.name())
                .map(ConcertGetAllByPageableResponse::from));
    }

    @GetMapping("/{id}")
    @Operation(summary = "콘서트 id로 콘서트 조회")
    public ResponseEntity<ResponseDto<GetConcertResponse>> get(@PathVariable @Positive Long id) {
        return ResponseDto.from(GET_CONCERT_BY_ID,
            GetConcertResponse.from(concertGetUseCase.getById(id)));
    }
}
