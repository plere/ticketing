package com.example.reservationservice.tempreservation.adapter.out.persistence;

import com.example.reservationservice.tempreservation.model.TempReservation;
import com.example.reservationservice.tempreservation.port.out.GetTempReservationPort;
import com.example.reservationservice.tempreservation.port.out.SaveTempReservationPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class TempReservationPersistenceAdapter implements GetTempReservationPort, SaveTempReservationPort {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    // waitingtoken::concert::waiting::position::{concert_id}
    private static final String POSITION_KEY_FORMAT = "reservation::temp::concert::%s::round::%s::user::%s";
    private static final Duration TEMP_RESERVATION_DURATION = Duration.ofMinutes(5);


    @Override
    public TempReservation get(TempReservation tempReservation) {
        String key = generateKey(tempReservation);
        return objectMapper.convertValue(redisTemplate.opsForValue().get(key), TempReservation.class);
    }

    @Override
    public TempReservation save(TempReservation tempReservation) {
        String key = generateKey(tempReservation);
        redisTemplate.opsForValue().set(key, tempReservation, TEMP_RESERVATION_DURATION);

        return tempReservation;
    }

    private String generateKey(TempReservation tempReservation) {
        return POSITION_KEY_FORMAT.formatted(tempReservation.concertId(), tempReservation.roundId(), tempReservation.userId());
    }
}
