package com.example.reservationservice.tempreservation.adapter.out.persistence.redis;

import com.example.reservationservice.tempreservation.adapter.out.persistence.HoldConcertCacheSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static com.example.reservationservice.tempreservation.domain.ConcertSeatState.AVAILABLE;
import static com.example.reservationservice.tempreservation.domain.ConcertSeatState.RESERVED;

@Component
@RequiredArgsConstructor
public class HoldConcertRedisCacheSeat implements HoldConcertCacheSeat {
    private static final String CONCERT_CACHE_SEAT_FORMATTED = "reservation::temp::seat::id::%d";
    private static final String CONCERT_CACHE_SEAT_TTL = String.valueOf(1000 * 30);
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisScript<Boolean> seatLockScript;

    @Override
    public boolean holdCacheSeats(Set<Long> seatIds) {
        List<String> keys = seatIds.stream()
            .map(generateCacheSeatKey())
            .toList();

        return stringRedisTemplate.execute(
            seatLockScript,
            keys,
            RESERVED.name(),
            CONCERT_CACHE_SEAT_TTL
        );
    }

    @Override
    public void releaseCacheSeats(Set<Long> seatIds) {
        List<String> keys = seatIds.stream()
            .map(generateCacheSeatKey())
            .toList();

        stringRedisTemplate.execute(
            seatLockScript,
            keys,
            AVAILABLE.name(),
            CONCERT_CACHE_SEAT_TTL
        );
    }

    private static Function<Long, String> generateCacheSeatKey() {
        return CONCERT_CACHE_SEAT_FORMATTED::formatted;
    }
}
