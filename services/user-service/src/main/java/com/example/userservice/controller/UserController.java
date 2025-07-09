package com.example.userservice.controller;

import com.example.httpresponse.response.CreatedResponseDto;
import com.example.httpresponse.response.ResponseDto;
import com.example.userservice.controller.dto.SignUpRequest;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.userservice.controller.dto.UserResponseCode.SIGNUP_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseDto<CreatedResponseDto> signUp(@RequestBody @Valid SignUpRequest request) {
        return CreatedResponseDto.from(userService.create(request), SIGNUP_SUCCESS);
    }
}
