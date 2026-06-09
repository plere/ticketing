package com.example.httpresponse.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatedResponseDto<T> {
    private static final int status = 201;
    private String url;
    private T data;

    public CreatedResponseDto(String url) {
        this.url = url;
    }

    public static CreatedResponseDto<Void> from(String url) {
        return new CreatedResponseDto<>(url);
    }

    public static <E extends Enum<E>> ResponseEntity<ResponseDto<CreatedResponseDto<Void>>> from(long id, E code) {
        return ResponseDto.from(CreatedResponseDto.status, code,
            CreatedResponseDto.from(
                ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUriString()
            )
        );
    }

    public static <T, E extends Enum<E>> ResponseEntity<ResponseDto<CreatedResponseDto<T>>> from(long id, E code, T data) {
        return ResponseDto.from(CreatedResponseDto.status, code,
            new CreatedResponseDto<>(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUriString(), data)
        );
    }
}
