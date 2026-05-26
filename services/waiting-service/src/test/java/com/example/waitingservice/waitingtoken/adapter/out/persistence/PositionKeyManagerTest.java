package com.example.waitingservice.waitingtoken.adapter.out.persistence;

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
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class PositionKeyManagerTest {
    @Autowired
    private PositionKeyManager positionKeyManager;
    @Autowired
    private StringRedisTemplate redisTemplate;

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
    public void position_key_저장_성공() {
        WaitingToken waitingToken = WaitingToken.builder()
            .id(1L)
            .token(UUID.randomUUID().toString())
            .build();

        positionKeyManager.savePositionKey(waitingToken, Duration.ofSeconds(30));

        String key = positionKeyManager.generatePositionKey(waitingToken);

        Set<String> result = redisTemplate.opsForZSet().range(key, 0, -1);


        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.iterator().next()).isEqualTo(waitingToken.token());
    }

    @Test
    public void position_key에_score_순으로_정렬() {
        WaitingToken firstToken = WaitingToken.builder()
            .id(1L)
            .token(UUID.randomUUID().toString())
            .build();

        WaitingToken secondToken = WaitingToken.builder()
            .id(1L)
            .token(UUID.randomUUID().toString())
            .build();

        positionKeyManager.savePositionKey(firstToken, Duration.ofSeconds(30));
        positionKeyManager.savePositionKey(secondToken, Duration.ofSeconds(30));

        String key = positionKeyManager.generatePositionKey(firstToken);

        Set<String> result = redisTemplate.opsForZSet().range(key, 0, -1);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);

        List<String> list = result.stream().toList();
        assertThat(list.get(0)).isEqualTo(firstToken.token());
        assertThat(list.get(1)).isEqualTo(secondToken.token());
    }

    @Test
    public void timeout시_키_제거() {
        WaitingToken token = WaitingToken.builder()
            .id(1L)
            .token(UUID.randomUUID().toString())
            .build();

        positionKeyManager.savePositionKey(token, Duration.ofMillis(1));

        String key = positionKeyManager.generatePositionKey(token);

        Set<String> result = redisTemplate.opsForZSet().range(key, 0, -1);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);

        positionKeyManager.deleteDummyWaitingToken(token);

        result = redisTemplate.opsForZSet().range(key, 0, -1);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void timeout_아니면_키_유지() {
        WaitingToken token = WaitingToken.builder()
            .id(1L)
            .token(UUID.randomUUID().toString())
            .build();

        positionKeyManager.savePositionKey(token, Duration.ofSeconds(30));

        String key = positionKeyManager.generatePositionKey(token);

        Set<String> result = redisTemplate.opsForZSet().range(key, 0, -1);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);

        positionKeyManager.deleteDummyWaitingToken(token);

        result = redisTemplate.opsForZSet().range(key, 0, -1);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void 존재하지않는_토큰_순서는_음수_리턴() {
        WaitingToken token_1 = WaitingToken.builder()
            .id(1L)
            .token(UUID.randomUUID().toString())
            .build();

        WaitingToken token_2 = WaitingToken.builder()
            .id(1L)
            .token(UUID.randomUUID().toString())
            .build();
        WaitingToken token_3 = WaitingToken.builder()
            .id(1L)
            .token(UUID.randomUUID().toString())
            .build();

        positionKeyManager.savePositionKey(token_1, Duration.ofSeconds(30));
        positionKeyManager.savePositionKey(token_2, Duration.ofSeconds(30));

        int position = positionKeyManager.getPosition(token_3, Duration.ofSeconds(30));
        assertThat(position).isEqualTo(-1);
    }


    @Test
    public void 순서_반환() {
        WaitingToken token_1 = WaitingToken.builder()
            .id(1L)
            .token(UUID.randomUUID().toString())
            .build();

        WaitingToken token_2 = WaitingToken.builder()
            .id(1L)
            .token(UUID.randomUUID().toString())
            .build();

        WaitingToken token_3 = WaitingToken.builder()
            .id(1L)
            .token(UUID.randomUUID().toString())
            .build();

        positionKeyManager.savePositionKey(token_1, Duration.ofSeconds(30));
        positionKeyManager.savePositionKey(token_2, Duration.ofSeconds(30));
        positionKeyManager.savePositionKey(token_3, Duration.ofSeconds(30));

        int position = positionKeyManager.getPosition(token_2, Duration.ofSeconds(30));
        assertThat(position).isEqualTo(2);
    }
}