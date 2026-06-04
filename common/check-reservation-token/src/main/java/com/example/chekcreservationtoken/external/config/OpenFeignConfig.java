package com.example.chekcreservationtoken.external.config;

import feign.QueryMapEncoder;
import feign.Retryer;
import feign.querymap.FieldQueryMapEncoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableFeignClients(basePackages = "com.example")
public class OpenFeignConfig {
    @Bean
    public Retryer.Default retryer() {
        return new Retryer.Default(100L, TimeUnit.SECONDS.toMillis(2L), 2);
    }

    @Bean
    public QueryMapEncoder queryMapEncoder() {
        return new FieldQueryMapEncoder();
    }
}
