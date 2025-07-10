package com.example.concertservice.concert.adapter.in.web.api;


import com.example.concertservice.concert.adapter.in.web.request.CreateRequest;
import com.example.concertservice.concert.adapter.in.web.request.ModifyConcertBasicRequest;
import com.example.concertservice.concert.adapter.in.web.request.ModifyConcertPlaceRequest;
import com.example.concertservice.concert.adapter.in.web.response.AdminConcertResponseCode;
import com.example.concertservice.concert.application.port.in.usecase.ConcertCreateUseCase;
import com.example.concertservice.concert.application.port.in.usecase.ConcertModifyBasicInfoUseCase;
import com.example.concertservice.concert.application.port.in.usecase.ConcertModifyPlaceInfoUseCase;
import com.example.concertservice.concert.application.port.in.usecase.ConcertStateToCloseUseCase;
import com.example.httpresponse.response.CreatedResponseDto;
import com.example.httpresponse.response.ModifyResponseDto;
import com.example.httpresponse.response.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/concerts")
public class AdminConcertController {
    private final ConcertCreateUseCase concertCreateUseCase;
    private final ConcertStateToCloseUseCase concertStateToCloseUseCase;
    private final ConcertModifyBasicInfoUseCase concertModifyBasicInfoUseCase;
    private final ConcertModifyPlaceInfoUseCase concertModifyPlaceInfoUseCase;

    @PostMapping
    public ResponseDto<CreatedResponseDto> create(@RequestBody @Valid CreateRequest request) {
        return CreatedResponseDto.from(concertCreateUseCase.create(request.toModel()), AdminConcertResponseCode.CREATE_CONCERT);
    }

    @PatchMapping("/{id}/basic")
    public ResponseDto<ModifyResponseDto> modifyBasic(@PathVariable Long id, @RequestBody @Valid ModifyConcertBasicRequest request) {
        concertModifyBasicInfoUseCase.modifyBasicInfo(request.toModifyCommand(id));

        return ModifyResponseDto.from(id, "concert");
    }

    @PatchMapping("/{id}/place")
    public ResponseDto<ModifyResponseDto> modifyPlace(@PathVariable Long id, @RequestBody @Valid ModifyConcertPlaceRequest request) {
        concertModifyPlaceInfoUseCase.modifyPlaceInfo(request.toModifyCommand(id));

        return ModifyResponseDto.from(id, "concert");
    }

    @PatchMapping("/{id}/state/close")
    public ResponseDto<ModifyResponseDto> updateStateToClose(@PathVariable Long id) {
        concertStateToCloseUseCase.changeStateToClose(id);

        return ModifyResponseDto.from(id, "concert");
    }
}
