package com.example.waitingservice.waitingtoken.adapter.out.persistence;

import com.example.waitingservice.waitingtoken.application.service.WaitingTokenGenerator;
import com.example.waitingservice.waitingtoken.model.WaitingToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class WaitingTokenPersistenceAdapterTest {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private WaitingTokenPersistenceAdapter waitingTokenPersistenceAdapter;
    @Autowired
    private PositionKeyManager positionKeyManager;
    @Autowired
    private WaitingTokenGenerator waitingTokenGenerator;


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
    public void 만료된_토큰은_제거하고_순서계산() {
        WaitingToken token_1 = waitingTokenGenerator.generate(1L, 1L);
        WaitingToken token_2 = waitingTokenGenerator.generate(1L, 1L);
        WaitingToken token_3 = waitingTokenGenerator.generate(1L, 1L);

        positionKeyManager.savePositionKey(token_1, Duration.ofSeconds(30));
        positionKeyManager.savePositionKey(token_2, Duration.ofMillis(1));
        positionKeyManager.savePositionKey(token_3, Duration.ofSeconds(30));

        int position = waitingTokenPersistenceAdapter.getMyPosition(token_3);

        assertThat(position).isEqualTo(2);
    }
}