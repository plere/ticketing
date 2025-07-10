package com.example.concertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.example")
public class ConcertServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcertServiceApplication.class, args);
    }

}
