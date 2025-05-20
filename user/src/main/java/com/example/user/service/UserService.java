package com.example.user.service;

import com.example.user.controller.dto.SignUpRequest;
import com.example.user.repository.UserRepository;
import com.example.user.service.validation.CreateUserValidation;
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
