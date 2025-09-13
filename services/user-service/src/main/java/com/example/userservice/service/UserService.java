package com.example.userservice.service;

import com.example.userservice.controller.dto.SignUpRequest;
import com.example.userservice.controller.dto.UserLoginResponseDto;
import com.example.userservice.external.auth.AuthClient;
import com.example.userservice.external.auth.config.ClientProperty;
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
    private final ClientProperty clientProperty;


    public long create(SignUpRequest request) {
        createUserValidation.validate(request);

        return userRepository.save(request.toModel(passwordEncryptor)).getId();
    }

    public UserLoginResponseDto login(String code) {
        return UserLoginResponseDto.from(
            authClient.login(
                getBasicAuth(),
                Map.of("grant_type", clientProperty.getGrant_type(),
                    "code", code,
                    "redirect_uri", clientProperty.getRedirect_uri()
                ))
        );
    }

    private String getBasicAuth() {
        return "Basic " + Base64.getEncoder()
            .encodeToString((
                    clientProperty.getClient_id()
                        + ":"
                        + clientProperty.getClient_secret()
                ).getBytes()
            );
    }
}
