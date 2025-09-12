package com.example.reservationservice.tempreservation.adapter.out.web;

import com.example.reservationservice.tempreservation.model.Concert;
import com.example.reservationservice.tempreservation.model.ConcertRound;
import com.example.reservationservice.tempreservation.model.ConcertSeat;
import com.example.reservationservice.tempreservation.model.ConcertSeatGrade;
import com.example.reservationservice.tempreservation.port.out.GetConcertPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Component
public class ConcertWebAdapter implements GetConcertPort {
    @Override
    public Concert getOrElseThrow(long id) {
        //Todo) call concert service
        return Concert.builder()
            .id(id)
            .name("첫번째 콘서트(수정)")
            .placeId(6L)
            .rounds(
                List.of(ConcertRound.builder()
                    .id(9L)
                    .startDateTime(LocalDateTime.of(2025, 12, 2, 18, 0))
                    .build())
            )
            .seats(
                List.of(
                    ConcertSeat.builder()
                        .id(29L)
                        .floor(1)
                        .rowOrder(1)
                        .rowName("VIP")
                        .rowOrder(1)
                        .columnNum(1)
                        .grade(
                            ConcertSeatGrade.builder()
                                .id(19L)
                                .name("VIP")
                                .price(1000)
                                .build()
                        )
                        .build()
                )
            )
            .build();
    }

    @Override
    public Boolean isEmptySeats(long concertId, Set<Long> concertSeatIds) {
        //Todo) call concert service
        return true;
    }
}
