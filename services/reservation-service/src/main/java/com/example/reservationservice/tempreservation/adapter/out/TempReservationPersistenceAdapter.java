package com.example.reservationservice.tempreservation.adapter.out;

import com.example.reservationservice.tempreservation.model.TempReservation;
import com.example.reservationservice.tempreservation.port.out.GetTempReservationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class TempReservationPersistenceAdapter implements GetTempReservationPort {
    private final RedisTemplate<String, Object> redisTemplate;

    // waitingtoken::concert::waiting::position::{concert_id}
    private static final String POSITION_KEY_FORMAT = "reservation::temp::concert::%s::user::%s";
    private static final Duration TEMP_RESERVATION_DURATION = Duration.ofMinutes(5);


    @Override
    public TempReservation get(TempReservation tempReservation) {
        String key = generateKey(tempReservation);
        return (TempReservation) redisTemplate.opsForValue().get(key);
    }

    private String generateKey(TempReservation tempReservation) {
        return POSITION_KEY_FORMAT.formatted(tempReservation.concertId(), tempReservation.userId());
    }

}
