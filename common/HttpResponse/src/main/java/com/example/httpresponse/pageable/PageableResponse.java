package com.example.httpresponse.pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;

@Getter
@Builder
@AllArgsConstructor
public class PageableResponse<T> {
    private PageableResponse.Meta meta;
    private List<T> items;

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Meta {
        private Long totalItems;
        private int itemsPerPage;
        private int totalPages;
        private int currentPage;
    }

    public <R> PageableResponse<R> map(Function<T, R> mapper) {
        return PageableResponse.<R>builder()
            .meta(meta)
            .items(items.stream().map(mapper).toList())
            .build();
    }
}
