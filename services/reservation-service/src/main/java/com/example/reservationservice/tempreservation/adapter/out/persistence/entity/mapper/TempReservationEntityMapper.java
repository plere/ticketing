package com.example.reservationservice.tempreservation.adapter.out.persistence.entity.mapper;

import com.example.reservationservice.tempreservation.adapter.out.persistence.entity.TempReservationEntity;
import com.example.reservationservice.tempreservation.model.TempReservation;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class TempReservationEntityMapper {

    public TempReservation mapToModel(TempReservationEntity entity) {
        return TempReservation.builder()
            .id(entity.getId())
            .userId(entity.getUserId())
            .concertId(entity.getConcertId())
            .roundId(entity.getRoundId())
            .seatIds(Set.copyOf(entity.getSeatIds()))
            .build();
    }

    public TempReservationEntity mapToEntity(TempReservation model) {
        return TempReservationEntity.builder()
            .id(model.id())
            .userId(model.userId())
            .concertId(model.concertId())
            .roundId(model.roundId())
            .seatIds(Set.copyOf(model.seatIds()))
            .build();
    }
}
