package com.example.concertservice.common;

import com.example.httpresponse.pageable.PageableRequest;
import com.example.httpresponse.pageable.PageableResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@UtilityClass
public class JpaPageableMapper {
    public Pageable toJpaPageable(PageableRequest pageable) {
        return PageRequest.of(pageable.getPage() - 1, pageable.getLimit());
    }

    public <T> PageableResponse<T> toCommonPageableResponse(Page<T> page) {
        return PageableResponse.<T>builder()
            .meta(PageableResponse.Meta.builder()
                .totalItems(page.getTotalElements())
                .itemsPerPage(page.getSize())
                .totalPages(page.getTotalPages())
                .currentPage(page.getNumber() + 1)
                .build())
            .items(page.getContent())
            .build();
    }
}
