package com.example.reservationservice.config;

import com.example.reservationservice.helper.HoldConcertCacheSeatHelper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

@TestConfiguration
public class TestConfig {
    @Bean
    public HoldConcertCacheSeatHelper holdConcertCacheSeatHelper(StringRedisTemplate stringRedisTemplate) {
        return new HoldConcertCacheSeatHelper(stringRedisTemplate);
    }
}
