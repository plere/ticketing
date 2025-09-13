package com.example.userservice.controller;

import com.example.httpresponse.response.CreatedResponseDto;
import com.example.httpresponse.response.ResponseDto;
import com.example.userservice.controller.dto.SignUpRequest;
import com.example.userservice.controller.dto.UserLoginResponseDto;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.userservice.controller.dto.UserResponseCode.LOGIN_SUCCESS;
import static com.example.userservice.controller.dto.UserResponseCode.SIGNUP_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/login")
    public ResponseDto<UserLoginResponseDto> login(@RequestParam String code) {
        return ResponseDto.from(LOGIN_SUCCESS, userService.login(code));
    }

    @PostMapping
    public ResponseDto<CreatedResponseDto> signUp(@RequestBody @Valid SignUpRequest request) {
        return CreatedResponseDto.from(userService.create(request), SIGNUP_SUCCESS);
    }
}
