package com.example.concertservice.concert.adapter.out.persistence.entity.mapper;

import com.example.concertservice.concert.adapter.out.persistence.entity.ConcertSeatGradeEntity;
import com.example.concertservice.concert.domain.ConcertSeatGrade;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConcertSeatGradeEntityMapper {
    public ConcertSeatGrade mapToModel(ConcertSeatGradeEntity entity) {
        return ConcertSeatGrade.builder()
            .id(entity.getId())
            .name(entity.getName())
            .price(entity.getPrice())
            .build();
    }

    public ConcertSeatGradeEntity mapToEntity(ConcertSeatGrade domain) {
        return ConcertSeatGradeEntity.builder()
            .name(domain.name())
            .price(domain.price())
            .build();
    }
}
