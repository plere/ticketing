package com.example.waitingservice.waitingtoken.adapter.out.persistence;

import com.example.httpresponse.exception.BadRequestException;
import com.example.httpresponse.exception.CommonErrorCode;
import com.example.httpresponse.exception.ServerException;
import com.example.waitingservice.waitingtoken.application.port.out.WaitingTokenRepository;
import com.example.waitingservice.waitingtoken.model.WaitingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

import static com.example.httpresponse.exception.CommonErrorCode.ARGUMENT_ERROR;

@Component
@RequiredArgsConstructor
public class WaitingTokenPersistenceAdapter implements WaitingTokenRepository {
    private final StringRedisTemplate redisTemplate;

    // waitingtoken::concert::waiting::position::{concert_id}
    private static final String POSITION_KEY_FORMAT = "waitingtoken::concert::waiting::position::%s";
    // waitingtoken::concert::user::{concert_id}::{key}
    private static final String USER_WAITING_TOKEN_KEY_FORMAT = "waitingtoken::concert::user::%s::%s";
    private static final Duration DEFAULT_DURATION = Duration.ofSeconds(65);


    @Override
    public WaitingToken save(WaitingToken waitingToken) {
        deleteDummyWaitingToken(waitingToken);
        savePositionKey(waitingToken);
        saveUserToken(waitingToken);

        return waitingToken;
    }

    @Override
    public int getMyPosition(WaitingToken waitingToken) {
        deleteDummyWaitingToken(waitingToken);

        String key = generatePositionKey(waitingToken);
        Long rank = redisTemplate.opsForZSet().rank(key, waitingToken.token());
        if (rank == null) {
            throw new BadRequestException(ARGUMENT_ERROR, ARGUMENT_ERROR.getErrorMessage());
        }

        return rank.intValue();
    }

    private void savePositionKey(WaitingToken waitingToken) {
        String key = generatePositionKey(waitingToken);
        long score = System.currentTimeMillis();
        int lockCount = 0;

        while (!redisTemplate.opsForZSet().add(key, waitingToken.token(), score) && lockCount < 5) {
            lockCount++;
        }

        if (lockCount >= 5) {
            throw new ServerException(CommonErrorCode.TEMPORARY_ERROR);
        }
    }

    private void saveUserToken(WaitingToken waitingToken) {
        String key = generateUserKey(waitingToken);
        redisTemplate.opsForValue().set(key, "", DEFAULT_DURATION);
    }

    private String generatePositionKey(WaitingToken waitingToken) {
        return POSITION_KEY_FORMAT.formatted(waitingToken.id());
    }

    private String generateUserKey(WaitingToken waitingToken) {
        return USER_WAITING_TOKEN_KEY_FORMAT.formatted(waitingToken.id(), waitingToken.token());
    }

    private void deleteDummyWaitingToken(WaitingToken waitingToken) {
        String key = generatePositionKey(waitingToken);
        Optional.ofNullable(redisTemplate.opsForZSet().range(key, 0, 100))
            .ifPresent(userKeys -> userKeys.forEach(userKey -> {
                String foundUserKey = redisTemplate.opsForValue()
                    .get(
                        generateUserKey(WaitingToken.builder()
                            .id(waitingToken.id())
                            .token(userKey)
                            .build()
                        )
                    );

                if (foundUserKey == null) {
                    redisTemplate.opsForZSet().remove(key, userKey);
                }
            }));
    }
}
