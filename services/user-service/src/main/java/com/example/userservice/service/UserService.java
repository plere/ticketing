package com.example.userservice.service;

import com.example.userservice.controller.dto.SignUpRequest;
import com.example.userservice.controller.dto.UserMeResponseDto;
import com.example.userservice.external.auth.AuthClient;
import com.example.userservice.external.auth.config.AuthProperty;
import com.example.userservice.external.auth.dto.LoginResponseDto;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.validation.CreateUserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;
    private final CreateUserValidation createUserValidation;
    private final AuthClient authClient;
    private final AuthProperty authProperty;

    public long create(SignUpRequest request) {
        createUserValidation.validate(request);

        return userRepository.save(request.toModel(passwordEncryptor)).getId();
    }

    public String oauthLogin(String state) {
        return authProperty.getOAuthLoginUri(state);
    }

    public LoginResponseDto login(String code) {
        return authClient.login(
            getBasicAuth(),
            Map.of("grant_type", authProperty.grant_type(),
                "code", code,
                "redirect_uri", authProperty.redirect_uri()
            ));
    }

    public LoginResponseDto getTokenByRefresh(String refreshToken) {
        return authClient.getToken(
            getBasicAuth(),
            Map.of("grant_type", authProperty.refresh_grant_type(),
                "refresh_token", refreshToken
            ));
    }

    public UserMeResponseDto getMe(long id) {
        return UserMeResponseDto.from(userRepository.findById(id)
            .orElseThrow());
    }

    private String getBasicAuth() {
        return "Basic " + Base64.getEncoder()
            .encodeToString((
                    authProperty.client_id()
                        + ":"
                        + authProperty.client_secret()
                ).getBytes()
            );
    }
}
