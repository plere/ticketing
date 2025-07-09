package com.example.userservice.service;

import com.example.userservice.controller.dto.SignUpRequest;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.validation.CreateUserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;
    private final CreateUserValidation createUserValidation;

    public long create(SignUpRequest request) {
        createUserValidation.validate(request);

        return userRepository.save(request.toModel(passwordEncryptor)).getId();
    }
}
