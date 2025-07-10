package com.example.concertservice.concert.adapter.out.internal;

import com.example.concertservice.concert.domain.PlaceSeat;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PlaceSeatMapper {
    public PlaceSeat mapToInConcertModel(com.example.concertservice.place.domain.PlaceSeat in) {
        return PlaceSeat.builder()
            .id(in.getId())
            .floor(in.getFloor())
            .rowOrder(in.getRowOrder())
            .rowName(in.getRowName())
            .rowCount(in.getRowCount())
            .build();
    }
}
