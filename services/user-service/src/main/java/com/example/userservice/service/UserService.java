package com.example.userservice.service;

import com.example.userservice.controller.dto.SignUpRequest;
import com.example.userservice.controller.dto.UserMeResponseDto;
import com.example.userservice.external.auth.AuthClient;
import com.example.userservice.external.auth.config.ClientProperty;
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
    private final ClientProperty clientProperty;


    public long create(SignUpRequest request) {
        createUserValidation.validate(request);

        return userRepository.save(request.toModel(passwordEncryptor)).getId();
    }

    public String oauthLogin(String state) {
        return " https://mock-ticketing.com:8084/oauth2/authorize?response_type=code&client_id=user-service&redirect_uri=https://mock-ticketing.com:8081/users/login&scope=profile&state=" + state;
    }

    public LoginResponseDto login(String code) {
        return authClient.login(
            getBasicAuth(),
            Map.of("grant_type", clientProperty.getGrant_type(),
                "code", code,
                "redirect_uri", clientProperty.getRedirect_uri()
            ));
    }

    public UserMeResponseDto getMe(long id) {
        return UserMeResponseDto.from(userRepository.findById(id)
            .orElseThrow());
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
