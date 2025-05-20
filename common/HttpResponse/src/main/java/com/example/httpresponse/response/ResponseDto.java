package com.example.httpresponse.response;

public record ResponseDto<T>(int status, String code, T body) {
    public static <T> ResponseDto<T> from(int status, String code, T body) {
        return new ResponseDto<>(status, code, body);
    }

    public static <T, R extends Enum<R>> ResponseDto<T> from(int status, R code, T body) {
        return new ResponseDto<>(status, code.name(), body);
    }

    public static <T, R extends Enum<R>> ResponseDto<T> from(R code, T body) {
        return new ResponseDto<>(200, code.name(), body);
    }
}
