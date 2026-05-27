package com.example.waitingservice.waitingtoken.application.service;

import com.example.waitingservice.waitingtoken.application.port.out.GetConcertPort;
import com.example.waitingservice.waitingtoken.application.port.out.GetReservationPort;
import com.example.waitingservice.waitingtoken.application.port.out.WaitingTokenRepository;
import com.example.waitingservice.waitingtoken.model.ConcertState;
import com.example.waitingservice.waitingtoken.model.WaitingToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class WaitingTokenServiceTest {
    @Autowired
    private WaitingTokenService waitingTokenService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @MockBean
    private GetConcertPort getConcertPort;
    @MockBean
    private GetReservationPort getReservationPort;
    @MockBean
    private WaitingTokenRepository waitingTokenRepository;

    @Container
    @ServiceConnection
    static GenericContainer<?> redis =
        new GenericContainer<>("redis:7-alpine")
            .withExposedPorts(6379);

    @BeforeEach
    void beforeEach() {
        Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().serverCommands().flushDb();
    }

    @Test
    void WaitingToken_생성_성공() {
        given(getConcertPort.getConcertState(anyLong()))
            .willReturn(ConcertState.OPEN);

        given(getConcertPort.isIncludedRound(anyLong(), anyLong()))
            .willReturn(true);

        WaitingToken token = waitingTokenService.create(1L, 1L);

        assertThat(token.id()).isEqualTo("1-1");
        assertThat(token.id()).isNotEmpty();
    }

    @Test
    void 예매서비스에서_남은_좌석을_뺀_결과값이_음수면_0리턴() {
        given(waitingTokenRepository.getMyPosition(any()))
            .willReturn(5);

        given(getReservationPort.availableProcessingCount(anyLong(), anyLong()))
            .willReturn(10);

        int result = waitingTokenService.getWaitingPosition(1L, 1L, UUID.randomUUID().toString());

        assertThat(result).isEqualTo(0);
    }

    @Test
    void 예매서비스에서_남은_좌석을_뺀_결과값이_양수면_그대로_리턴() {
        given(waitingTokenRepository.getMyPosition(any()))
            .willReturn(5);

        given(getReservationPort.availableProcessingCount(anyLong(), anyLong()))
            .willReturn(0);

        int result = waitingTokenService.getWaitingPosition(1L, 1L, UUID.randomUUID().toString());

        assertThat(result).isEqualTo(5);
    }
}