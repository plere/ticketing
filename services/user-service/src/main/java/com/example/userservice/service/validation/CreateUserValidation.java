package com.example.userservice.service.validation;

import com.example.httpresponse.exception.BadRequestException;
import com.example.userservice.controller.dto.SignUpRequest;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.userservice.controller.dto.UserErrorResponseCode.DUPLICATED_EMAIL;

@Component
@RequiredArgsConstructor
public class CreateUserValidation {
    private final UserRepository userRepository;

    public void validate(SignUpRequest request) {
        checkDuplicatedEmail(request);
    }

    private void checkDuplicatedEmail(SignUpRequest request) {
        Optional<User> user = userRepository.findByEmail(request.email());
        if (user.isPresent()) {
            throw new BadRequestException(DUPLICATED_EMAIL, DUPLICATED_EMAIL.getErrorMessage());
        }
    }
}
