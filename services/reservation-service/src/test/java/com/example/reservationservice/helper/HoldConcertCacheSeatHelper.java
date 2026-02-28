package com.example.reservationservice.helper;

import com.example.reservationservice.reservation.domain.ConcertSeatState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public class HoldConcertCacheSeatHelper {
    private final StringRedisTemplate stringRedisTemplate;
    private static final String CONCERT_CACHE_SEAT_FORMATTED = "reservation::temp::seat::id::%d";

    public Map<Long, ConcertSeatState> getCacheSeats(List<Long> seatIds) {
        List<String> states = stringRedisTemplate.opsForValue().multiGet(seatIds.stream().map(generateCacheSeatKey()).toList());

        if (states == null) {
            return new HashMap<>();
        }

        Map<Long, ConcertSeatState> cacheSeats = new HashMap<>();
        for (int i = 0; i < states.size(); i++) {
            cacheSeats.put(seatIds.get(i), ConcertSeatState.valueOf(states.get(i)));
        }

        return cacheSeats;
    }

    private static Function<Long, String> generateCacheSeatKey() {
        return CONCERT_CACHE_SEAT_FORMATTED::formatted;
    }
}
