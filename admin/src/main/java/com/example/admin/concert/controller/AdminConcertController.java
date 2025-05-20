package com.example.admin.concert.controller;


import com.example.admin.concert.controller.dto.CreateRequest;
import com.example.admin.concert.controller.dto.ModifyConcertBasicRequest;
import com.example.admin.concert.controller.dto.ModifyConcertPlaceRequest;
import com.example.admin.concert.service.AdminConcertService;
import com.example.httpresponse.response.CreatedResponseDto;
import com.example.httpresponse.response.ModifyResponseDto;
import com.example.httpresponse.response.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.admin.concert.controller.dto.AdminConcertResponseCode.CREATE_CONCERT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/concerts")
public class AdminConcertController {
    private final AdminConcertService concertService;

    @PostMapping
    public ResponseDto<CreatedResponseDto> create(@RequestBody @Valid CreateRequest concert) {
        return CreatedResponseDto.from(concertService.create(concert), CREATE_CONCERT);
    }

    @PatchMapping("/{id}/basic")
    public ResponseDto<ModifyResponseDto> modifyBasic(@PathVariable Long id, @RequestBody @Valid ModifyConcertBasicRequest request) {
        concertService.modifyBasic(id, request);

        return ModifyResponseDto.from(id, "concert");
    }

    @PatchMapping("/{id}/place")
    public ResponseDto<ModifyResponseDto> modifyPlace(@PathVariable Long id, @RequestBody @Valid ModifyConcertPlaceRequest request) {
        concertService.modifyPlace(id, request);

        return ModifyResponseDto.from(id, "concert");
    }

    @PatchMapping("/{id}/state/close")
    public ResponseDto<ModifyResponseDto> updateStateToClose(@PathVariable Long id) {
        concertService.updateStateToClose(id);

        return ModifyResponseDto.from(id, "concert");
    }
}
