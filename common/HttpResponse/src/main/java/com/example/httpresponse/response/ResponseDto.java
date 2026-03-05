package com.example.httpresponse.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
    private String code;
    private T body;

    public static <T> ResponseEntity<ResponseDto<T>> from(int status, String code, T body) {
        return ResponseEntity.status(status)
            .body(
                ResponseDto.<T>builder()
                    .code(code)
                    .body(body)
                    .build()
            );
    }

    public static <T, R extends Enum<R>> ResponseEntity<ResponseDto<T>> from(int status, R code, T body) {
        return ResponseEntity.status(status)
            .body(
                ResponseDto.<T>builder()
                    .code(code.name())
                    .body(body)
                    .build()
            );
    }

    public static <T, R extends Enum<R>> ResponseEntity<ResponseDto<T>> from(R code, T body) {
        return ResponseEntity.status(200)
            .body(
                ResponseDto.<T>builder()
                    .code(code.name())
                    .body(body)
                    .build()
            );
    }
}
