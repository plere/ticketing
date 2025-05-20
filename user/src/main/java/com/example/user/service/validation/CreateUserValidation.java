package com.example.user.service.validation;

import com.example.httpresponse.exception.BadRequestException;
import com.example.user.controller.dto.SignUpRequest;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.user.controller.dto.UserErrorResponseCode.DUPLICATED_EMAIL;

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
