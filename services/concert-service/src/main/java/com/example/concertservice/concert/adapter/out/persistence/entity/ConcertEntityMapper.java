package com.example.concertservice.concert.adapter.out.persistence.entity;

import com.example.concertservice.concert.domain.Concert;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ConcertEntityMapper {
    public Concert mapToModel(ConcertEntity entity) {
        return Concert.builder()
            .id(entity.getId())
            .name(entity.getName())
            .detailInfo(entity.getDetailInfo())
            .runningTime(entity.getRunningTime())
            .state(entity.getState())
            .ticketingStartTime(entity.getTicketingStartTime())
            .openTime(entity.getOpenTime())
            .placeId(entity.getPlaceId())
            .placeName(entity.getPlaceName())
            .rounds(entity.getRounds().stream().map(ConcertRoundEntityMapper::mapToModel).toList())
            .seatGrades(entity.getSeatGrades().stream().map(ConcertSeatGradeEntityMapper::mapToModel).toList())
            .seats(entity.getSeats().stream()
                .map(ConcertSeatEntityMapper::mapToModel)
                .toList())
            .build();
    }

    public ConcertEntity mapToEntity(Concert domain) {
        List<ConcertSeatGradeEntity> seatGradeEntities = domain.seatGrades().stream().map(ConcertSeatGradeEntityMapper::mapToEntity).toList();
        return ConcertEntity.builder()
            .id(domain.id())
            .name(domain.name())
            .detailInfo(domain.detailInfo())
            .runningTime(domain.runningTime())
            .state(domain.state())
            .ticketingStartTime(domain.ticketingStartTime())
            .openTime(domain.openTime())
            .placeId(domain.placeId())
            .placeName(domain.placeName())
            .rounds(domain.rounds().stream().map(ConcertRoundEntityMapper::mapToEntity).toList())
            .seatGrades(seatGradeEntities)
            .seats(domain.seats().stream()
                .map(seat -> ConcertSeatEntityMapper.mapToEntity(seat, seatGradeEntities))
                .toList())
            .build();
    }
}
