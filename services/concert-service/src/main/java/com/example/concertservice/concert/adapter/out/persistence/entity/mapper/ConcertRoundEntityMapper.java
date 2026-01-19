package com.example.concertservice.concert.adapter.out.persistence.entity.mapper;

import com.example.concertservice.concert.adapter.out.persistence.entity.ConcertRoundEntity;
import com.example.concertservice.concert.domain.ConcertRound;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConcertRoundEntityMapper {
    public ConcertRound mapToModel(ConcertRoundEntity entity) {
        return ConcertRound.builder()
            .id(entity.getId())
            .startDateTime(entity.getStartDateTime())
            .sequenceNumber(entity.getSequenceNumber())
            .build();
    }

    public ConcertRoundEntity mapToEntity(ConcertRound domain) {
        return ConcertRoundEntity.builder()
            .id(domain.id())
            .startDateTime(domain.startDateTime())
            .sequenceNumber(domain.sequenceNumber())
            .build();
    }
}
