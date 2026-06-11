package com.example.reservationservice.common.config;

import feign.Retryer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.example.reservationservice")
public class OpenFeignConfig {
    @Bean
    public Retryer.Default retryer() {
        return new Retryer.Default(100L, 5000, 2);
    }
}
