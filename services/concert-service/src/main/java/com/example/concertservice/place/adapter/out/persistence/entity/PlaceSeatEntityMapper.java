package com.example.concertservice.place.adapter.out.persistence.entity;

import com.example.concertservice.place.domain.PlaceSeat;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PlaceSeatEntityMapper {
    public PlaceSeat mapToModel(PlaceSeatEntity entity) {
        return PlaceSeat.builder()
            .id(entity.getId())
            .floor(entity.getFloor())
            .rowOrder(entity.getRowOrder())
            .rowName(entity.getRowName())
            .rowCount(entity.getRowCount())
            .build();
    }
}
