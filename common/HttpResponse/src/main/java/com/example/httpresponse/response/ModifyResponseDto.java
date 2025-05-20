package com.example.httpresponse.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyResponseDto {
    private static final int status = 200;
    private static final String code = "MODIFY_SUCCESS";
    private static final String format = "%s:(%d) 수정완료";
    private final String message;

    public static ResponseDto<ModifyResponseDto> from(long id, String model) {
        return ResponseDto.from(ModifyResponseDto.status, ModifyResponseDto.code,
            new ModifyResponseDto(ModifyResponseDto.format.formatted(model, id))
        );
    }
}
