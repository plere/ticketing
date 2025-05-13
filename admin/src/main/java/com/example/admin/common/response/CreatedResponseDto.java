package com.example.admin.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Getter
@AllArgsConstructor
public class CreatedResponseDto {
    private static final int status = 201;
    private String url;

    public static CreatedResponseDto from(String url) {
        return new CreatedResponseDto(url);
    }

    public static <T extends Enum<T>> ResponseDto<CreatedResponseDto> from(long id, T code) {
        return ResponseDto.from(CreatedResponseDto.status, code,
            CreatedResponseDto.from(
                ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUriString()
            )
        );
    }
}
