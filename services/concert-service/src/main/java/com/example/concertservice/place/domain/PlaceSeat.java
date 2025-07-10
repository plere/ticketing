package com.example.concertservice.place.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSeat {
    private Long id;
    private Integer floor;
    private Integer rowOrder;
    private String rowName;
    private Integer rowCount;
}
