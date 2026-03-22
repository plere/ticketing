package com.example.paymentservice.adapter.out.web.toss.config;

import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Configuration
public class TossWebClientConfiguration {
    @Value("${PSP.toss.url}")
    private String baseUrl;

    @Value("${PSP.toss.secretKey}")
    private String secretKey;

    @Bean
    public WebClient tossPaymentWebClient() {
        String encodedSecretKey = Base64.getEncoder().encodeToString((secretKey + ":").getBytes());

        return WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodedSecretKey)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .clientConnector(reactorClientHttpConnector())
            .codecs(ClientCodecConfigurer::defaultCodecs)
            .build();
    }

    private ClientHttpConnector reactorClientHttpConnector() {
        ConnectionProvider provider = ConnectionProvider.builder("toss-payment").build();

        HttpClient clientBase = HttpClient.create(provider)
            .doOnConnected(it ->
                it.addHandlerLast(new ReadTimeoutHandler(30, TimeUnit.SECONDS))
            );

        return new ReactorClientHttpConnector(clientBase);
    }
}
