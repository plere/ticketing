package com.example.userservice.controller;

import com.example.checkauth.UserToken;
import com.example.httpresponse.response.CreatedResponseDto;
import com.example.httpresponse.response.ResponseDto;
import com.example.userservice.controller.dto.SignUpRequest;
import com.example.userservice.controller.dto.UserMeResponseDto;
import com.example.userservice.external.auth.dto.LoginResponseDto;
import com.example.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.userservice.controller.dto.UserResponseCode.GET_ME;
import static com.example.userservice.controller.dto.UserResponseCode.SIGNUP_SUCCESS;

@Tag(name = "User/user")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/oauth2/login")
    @Operation(summary = "oauth2를 통한 로그인 API")
    public ResponseEntity<Void> oauthLogin(@RequestParam String state) {
        return ResponseEntity
            .status(HttpStatus.FOUND)
            .header(HttpHeaders.LOCATION, userService.oauthLogin(state))
            .build();
    }

    @GetMapping(value = "/login")
    @Operation(summary = "oauth2 로그인 리다이렉트 API, user 호출 X")
    public ResponseEntity<Void> login(@RequestParam String code, @RequestParam String state) {
        LoginResponseDto loginInfo = userService.login(code);

        return ResponseEntity
            .status(HttpStatus.FOUND)
            .header(HttpHeaders.LOCATION, state)
            .header(HttpHeaders.SET_COOKIE, ResponseCookie.from("accessToken", loginInfo.access_token())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60)
                .domain("mock-ticketing.com")
                .build().toString())
            .header(HttpHeaders.SET_COOKIE, ResponseCookie.from("refreshToken", loginInfo.refresh_token())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(1800)
                .domain("mock-ticketing.com")
                .build().toString())
            .build();
    }

    @GetMapping("/me")
    @Operation(summary = "내 정보 조회 API")
    public ResponseEntity<ResponseDto<UserMeResponseDto>> me(UserToken userToken) {
        return ResponseDto.from(GET_ME, userService.getMe(userToken.getId()));
    }

    @PostMapping
    @Operation(summary = "회원가입 API")
    public ResponseEntity<ResponseDto<CreatedResponseDto>> signUp(@RequestBody @Valid SignUpRequest request) {
        return CreatedResponseDto.from(userService.create(request), SIGNUP_SUCCESS);
    }
}
