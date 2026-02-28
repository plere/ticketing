package com.example.reservationservice.tempreservation.service;

import com.example.reservationservice.config.TestConfig;
import com.example.reservationservice.helper.HoldConcertCacheSeatHelper;
import com.example.reservationservice.reservation.adapter.out.persistence.TempReservationPersistenceAdapter;
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
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;

@Testcontainers
@SpringBootTest
@Import(TestConfig.class)
public class CreateTempReservationFailTest {
    @Autowired
    private TempReservationService tempReservationService;
    @Autowired
    private HoldConcertCacheSeatHelper holdConcertCacheSeatHelper;
    @MockBean
    private GetConcertPort getConcertPort;
    @MockBean
    private ConcertSeatWebAdapter concertSeatWebAdapter;
    @MockBean
    private TempReservationPersistenceAdapter tempReservationPersistenceAdapter;


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
    public void 좌석선점_DB_락_실패시_좌석상태_캐시값은_AVAILABLE() {
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
        willThrow(new RuntimeException())
            .given(concertSeatWebAdapter)
            .holdSeats(Set.of(1L, 2L));

        try {
            tempReservationService.createAndHoldSeats(TempReservation.builder()
                .userId(1L)
                .concertId(1L)
                .roundId(1L)
                .seatIds(Set.of(1L, 2L))
                .build());
            fail("실패해야한다");
        } catch (Exception e) {
            Map<Long, ConcertSeatState> cacheSeats = holdConcertCacheSeatHelper.getCacheSeats(List.of(1L, 2L));

            assertThat(cacheSeats.values().stream().allMatch(state -> state == ConcertSeatState.AVAILABLE)).isTrue();
        }
    }

    @Test
    @Transactional
    public void 임시예매생성_실패시_캐시_AVAILABLE() {
        TempReservation tempReservation = TempReservation.builder()
            .userId(1L)
            .concertId(1L)
            .roundId(1L)
            .seatIds(Set.of(1L, 2L))
            .build();

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
        willThrow(new RuntimeException())
            .given(tempReservationPersistenceAdapter).save(tempReservation);

        try {
            tempReservationService.createAndHoldSeats(tempReservation);
            fail("실패해야한다");
        } catch (Exception e) {
            Map<Long, ConcertSeatState> cacheSeats = holdConcertCacheSeatHelper.getCacheSeats(List.of(1L, 2L));

            assertThat(cacheSeats.values().stream().allMatch(state -> state == ConcertSeatState.AVAILABLE)).isTrue();
        }
    }
}
