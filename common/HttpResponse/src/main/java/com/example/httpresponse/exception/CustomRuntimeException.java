package com.example.httpresponse.exception;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {
    private final int status;
    private final String code;

    public CustomRuntimeException(int status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }
}
