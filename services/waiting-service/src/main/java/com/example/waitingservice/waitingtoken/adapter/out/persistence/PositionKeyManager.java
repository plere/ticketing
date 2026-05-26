package com.example.waitingservice.waitingtoken.adapter.out.persistence;

import com.example.httpresponse.exception.CommonErrorCode;
import com.example.httpresponse.exception.ServerException;
import com.example.waitingservice.waitingtoken.model.WaitingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PositionKeyManager {
    // waitingtoken::concert::waiting::position::{concert_id}
    private static final String POSITION_KEY_FORMAT = "waitingtoken::concert::waiting::position::%s";
    // waitingtoken::concert::validation::token::{concert_id}::{key}
    private static final String VALIDATION_TOKEN_KEY_FORMAT = "waitingtoken::concert::validation::token::%s::%s";
    private final StringRedisTemplate redisTemplate;

    public void savePositionKey(WaitingToken waitingToken, Duration ttl) {
        String key = generatePositionKey(waitingToken);
        long score = System.currentTimeMillis();
        int lockCount = 0;

        while (Boolean.FALSE.equals(redisTemplate.opsForZSet().add(key, waitingToken.token(), score)) && lockCount < 5) {
            lockCount++;
        }

        if (lockCount >= 5) {
            throw new ServerException(CommonErrorCode.TEMPORARY_ERROR);
        } else {
            saveValidationToken(waitingToken, ttl);
        }
    }

    public void deleteDummyWaitingToken(WaitingToken waitingToken) {
        String key = generatePositionKey(waitingToken);
        Optional.ofNullable(redisTemplate.opsForZSet().range(key, 0, 100))
            .ifPresent(values -> values.forEach((token) -> {
                String foundValidationKey = redisTemplate.opsForValue().get(
                    generateValidationKey(WaitingToken.builder()
                        .id(waitingToken.id())
                        .token(token)
                        .build()));

                if (foundValidationKey == null) {
                    redisTemplate.opsForZSet().remove(key, token);
                }
            }));
    }

    public int getPosition(WaitingToken waitingToken, Duration ttl) {
        String key = generatePositionKey(waitingToken);
        Long rank = redisTemplate.opsForZSet().rank(key, waitingToken.token());
        if (rank == null) {
            return -1;
        }

        saveValidationToken(waitingToken, ttl);
        return rank.intValue() + 1;
    }

    public String generatePositionKey(WaitingToken waitingToken) {
        return POSITION_KEY_FORMAT.formatted(waitingToken.id());
    }

    private void saveValidationToken(WaitingToken waitingToken, Duration ttl) {
        String key = generateValidationKey(waitingToken);

        redisTemplate.opsForValue().set(key, UUID.randomUUID().toString(), ttl);
    }

    private String generateValidationKey(WaitingToken waitingToken) {
        return VALIDATION_TOKEN_KEY_FORMAT.formatted(waitingToken.id(), waitingToken.token());
    }
}
