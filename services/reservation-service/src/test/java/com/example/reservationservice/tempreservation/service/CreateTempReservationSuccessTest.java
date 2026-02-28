package com.example.reservationservice.tempreservation.service;

import com.example.reservationservice.config.TestConfig;
import com.example.reservationservice.helper.HoldConcertCacheSeatHelper;
import com.example.reservationservice.reservation.adapter.out.web.ConcertSeatWebAdapter;
import com.example.reservationservice.reservation.application.service.TempReservationService;
import com.example.reservationservice.reservation.domain.*;
import com.example.reservationservice.reservation.application.port.out.GetConcertPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@Testcontainers
@SpringBootTest
@Import(TestConfig.class)
public class CreateTempReservationSuccessTest {
    @Autowired
    private TempReservationService tempReservationService;
    @Autowired
    private HoldConcertCacheSeatHelper holdConcertCacheSeatHelper;
    @MockBean
    private GetConcertPort getConcertPort;
    @MockBean
    private ConcertSeatWebAdapter concertSeatWebAdapter;

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
        .withCommand("--innodb-lock-wait-timeout=2")
        .withReuse(true);

    @Container
    @ServiceConnection
    static GenericContainer<?> redis =
        new GenericContainer<>("redis:7-alpine")
            .withExposedPorts(6379);

    @Test
    @Transactional
    public void 좌석선점시_임시예약도_생성() {
        given(getConcertPort.getOrElseThrow(1L))
            .willReturn(Concert.builder()
                .id(1L)
                .rounds(List.of(
                    ConcertRound.builder()
                        .id(1L)
                        .build()
                ))
                .seats(List.of(
                    ConcertSeat.builder()
                        .id(1L)
                        .build(),
                    ConcertSeat.builder()
                        .id(2L)
                        .build()
                ))
                .build());

        tempReservationService.createAndHoldSeats(TempReservation.builder()
            .userId(1L)
            .concertId(1L)
            .roundId(1L)
            .seatIds(Set.of(1L, 2L))
            .build());

        TempReservation tempReservation = tempReservationService.get(TempReservation.builder()
            .userId(1L)
            .roundId(1L)
            .build());

        assertThat(tempReservation).isNotNull();
        assertThat(tempReservation.concertId()).isEqualTo(1L);
        assertThat(tempReservation.seatIds()).containsAll(Set.of(1L, 2L));
    }

    @Test
    @Transactional
    public void 좌석선점시_레디스에_RESERVED상태_캐시생성() {
        given(getConcertPort.getOrElseThrow(1L))
            .willReturn(Concert.builder()
                .id(1L)
                .rounds(List.of(
                    ConcertRound.builder()
                        .id(1L)
                        .build()
                ))
                .seats(List.of(
                    ConcertSeat.builder()
                        .id(1L)
                        .build(),
                    ConcertSeat.builder()
                        .id(2L)
                        .build()
                ))
                .build());

        tempReservationService.createAndHoldSeats(TempReservation.builder()
            .userId(1L)
            .concertId(1L)
            .roundId(1L)
            .seatIds(Set.of(1L, 2L))
            .build());

        Map<Long, ConcertSeatState> cacheSeats = holdConcertCacheSeatHelper.getCacheSeats(List.of(1L, 2L));

        assertThat(cacheSeats.values().stream().allMatch(state -> state == ConcertSeatState.RESERVED)).isTrue();
    }
}
