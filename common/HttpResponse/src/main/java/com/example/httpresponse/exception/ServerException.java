package com.example.httpresponse.exception;

import org.springframework.http.HttpStatus;

public class ServerException extends CustomRuntimeException {
    private static final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final String message = "Server exception";

    public ServerException() {
        super(status.value(), status.name(), message);
    }
}
