package com.example.concertservice.concert.adapter.out.persistence.entity.mapper;

import com.example.concertservice.concert.adapter.out.persistence.entity.ConcertRoundEntity;
import com.example.concertservice.concert.adapter.out.persistence.entity.ConcertSeatEntity;
import com.example.concertservice.concert.adapter.out.persistence.entity.ConcertSeatGradeEntity;
import com.example.concertservice.concert.domain.ConcertSeat;
import com.example.httpresponse.exception.ServerException;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ConcertSeatEntityMapper {
    public ConcertSeat mapToModel(ConcertSeatEntity entity) {
        return ConcertSeat.builder()
            .id(entity.getId())
            .floor(entity.getFloor())
            .rowOrder(entity.getRowOrder())
            .rowName(entity.getRowName())
            .columnNum(entity.getColumnNum())
            .state(entity.getState())
            .grade(ConcertSeatGradeEntityMapper.mapToModel(entity.getGrade()))
            .roundId(entity.getRound().getId())
            .build();
    }

    public ConcertSeatEntity mapToEntity(ConcertSeat domain, List<ConcertSeatGradeEntity> seatGradeEntities, ConcertRoundEntity roundEntity) {
        return ConcertSeatEntity.builder()
            .id(domain.id())
            .floor(domain.floor())
            .rowOrder(domain.rowOrder())
            .rowName(domain.rowName())
            .columnNum(domain.columnNum())
            .state(domain.state())
            .grade(
                seatGradeEntities.stream()
                    .filter(gradeEntity -> gradeEntity.getName().equals(domain.getGradeName()))
                    .findAny()
                    .orElseThrow(ServerException::new)
            )
            .round(roundEntity)
            .build();
    }
}
