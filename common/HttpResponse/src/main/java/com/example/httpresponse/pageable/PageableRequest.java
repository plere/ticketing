package com.example.httpresponse.pageable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
public class PageableRequest {
    @Positive
    @NotNull
    protected Integer page;

    @Positive
    @NotNull
    protected Integer limit;
}
