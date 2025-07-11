package com.example.waitingservice.reservationtoken.adapter.out.persistence;

import com.example.waitingservice.reservationtoken.application.port.out.CreateReservationTokenPort;
import com.example.waitingservice.reservationtoken.model.ReservationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class ReservationTokenPersistenceAdapter implements CreateReservationTokenPort {
    private final StringRedisTemplate redisTemplate;

    // reservationtoken::concert::{concert_id}::{token}
    private static final String KEY_FORMAT = "reservationtoken::concert::%s::%s";
    private static final Duration DEFAULT_DURATION = Duration.ofMinutes(10);

    @Override
    public ReservationToken create(ReservationToken reservationToken) {
        String key = generateKey(reservationToken);

        redisTemplate.opsForValue().set(key, "", DEFAULT_DURATION);

        return reservationToken;
    }

    private String generateKey(ReservationToken reservationToken) {
        return KEY_FORMAT.formatted(reservationToken.id(), reservationToken.token());
    }
}
