package com.example.admin.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomRuntimeException {
    private static final HttpStatus status = HttpStatus.BAD_REQUEST;

    public <T extends Enum<T>> BadRequestException(T code, String message) {
        super(status.value(), code.name(), message);
    }
}
