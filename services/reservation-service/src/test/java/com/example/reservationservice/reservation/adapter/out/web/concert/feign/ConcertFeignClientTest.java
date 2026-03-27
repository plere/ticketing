package com.example.reservationservice.reservation.adapter.out.web.concert.feign;

import com.example.httpresponse.response.ResponseDto;
import com.example.reservationservice.reservation.adapter.out.web.concert.feign.dto.GetConcertResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureWireMock(port = 0)
class ConcertFeignClientTest {
    @Autowired
    ConcertFeignClient concertFeignClient;

    @Autowired
    CircuitBreakerRegistry circuitBreakerRegistry;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void ok() throws JsonProcessingException {
        var responseDto = ResponseDto.<GetConcertResponse>builder()
            .body(GetConcertResponse.builder()
                .id(1L)
                .name("콘서트명")
                .build())
            .build();

        stubFor(get(urlEqualTo("/internal/concerts/1"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(objectMapper.writeValueAsString(responseDto))
            )
        );

        var concert = concertFeignClient.getConcert(1L);

        assertThat(concert.getBody().getName()).isEqualTo("콘서트명");
    }


    @Test
    void slowCall_open_and_no_more_http_call() throws JsonProcessingException {
        var responseDto = ResponseDto.<GetConcertResponse>builder()
            .body(GetConcertResponse.builder()
                .id(1L)
                .name("콘서트명")
                .build())
            .build();

        stubFor(get(urlEqualTo("/internal/concerts/1"))
            .willReturn(aResponse()
                .withFixedDelay(1500)
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(
                    objectMapper.writeValueAsString(responseDto)
                )));

        for (int i = 0; i < 5; i++) {
            try {
                concertFeignClient.getConcert(1L);
            } catch (Exception ignored) {
            }
        }

        CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("ConcertFeignClientgetConcertLong");
        assertThat(cb.getState()).isEqualTo(CircuitBreaker.State.OPEN);

        verify(5, getRequestedFor(urlEqualTo("/internal/concerts/1")));
    }
}