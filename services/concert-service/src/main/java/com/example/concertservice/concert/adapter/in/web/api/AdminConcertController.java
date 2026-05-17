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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Concert/Admin")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/concerts")
public class AdminConcertController {
    private final ConcertCreateUseCase concertCreateUseCase;
    private final ConcertStateToCloseUseCase concertStateToCloseUseCase;
    private final ConcertModifyBasicInfoUseCase concertModifyBasicInfoUseCase;
    private final ConcertModifyPlaceInfoUseCase concertModifyPlaceInfoUseCase;

    @PostMapping
    @Operation(summary = "콘서트 생성")
    public ResponseEntity<ResponseDto<CreatedResponseDto>> create(@RequestBody @Valid CreateRequest request) {
        return CreatedResponseDto.from(concertCreateUseCase.create(request.toModel()), AdminConcertResponseCode.CREATE_CONCERT);
    }

    @PatchMapping("/{id}/basic")
    @Operation(summary = "콘서트 기본 정보 수정")
    public ResponseEntity<ResponseDto<ModifyResponseDto>> modifyBasic(@PathVariable Long id, @RequestBody @Valid ModifyConcertBasicRequest request) {
        concertModifyBasicInfoUseCase.modifyBasicInfo(request.toModifyCommand(id));

        return ModifyResponseDto.from(id, "concert");
    }

    @PatchMapping("/{id}/place")
    @Operation(summary = "콘서트 장소 수정")
    public ResponseEntity<ResponseDto<ModifyResponseDto>> modifyPlace(@PathVariable Long id, @RequestBody @Valid ModifyConcertPlaceRequest request) {
        concertModifyPlaceInfoUseCase.modifyPlaceInfo(request.toModifyCommand(id));

        return ModifyResponseDto.from(id, "concert");
    }

    @PatchMapping("/{id}/state/close")
    @Operation(summary = "콘서트 상태를 close로 변경")
    public ResponseEntity<ResponseDto<ModifyResponseDto>> updateStateToClose(@PathVariable Long id) {
        concertStateToCloseUseCase.changeStateToClose(id);

        return ModifyResponseDto.from(id, "concert");
    }
}
