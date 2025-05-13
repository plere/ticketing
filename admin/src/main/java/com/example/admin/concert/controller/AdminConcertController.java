package com.example.admin.concert.controller;

import com.example.admin.common.response.CreatedResponseDto;
import com.example.admin.common.response.ResponseDto;
import com.example.admin.concert.controller.dto.CreateRequest;
import com.example.admin.concert.service.AdminConcertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
