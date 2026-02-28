package com.example.reservationservice.reservation.adapter.out.persistence.entity.mapper;

import com.example.reservationservice.reservation.adapter.out.persistence.entity.ReservationEntity;
import com.example.reservationservice.reservation.domain.ReservationStatus;
import com.example.reservationservice.reservation.domain.TempReservation;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class TempReservationEntityMapper {

    public TempReservation mapToModel(ReservationEntity entity) {
        return TempReservation.builder()
            .id(entity.getId())
            .userId(entity.getUserId())
            .concertId(entity.getConcertId())
            .roundId(entity.getRoundId())
            .seatIds(Set.copyOf(entity.getSeatIds()))
            .build();
    }

    public ReservationEntity mapToEntity(TempReservation model) {
        return ReservationEntity.builder()
            .id(model.id())
            .userId(model.userId())
            .concertId(model.concertId())
            .roundId(model.roundId())
            .seatIds(Set.copyOf(model.seatIds()))
            .status(ReservationStatus.TEMP)
            .build();
    }
}
