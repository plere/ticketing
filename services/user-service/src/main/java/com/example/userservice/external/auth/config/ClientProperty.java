package com.example.userservice.external.auth.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("external.client.auth")
public class ClientProperty {
    private final String client_id;
    private final String client_secret;
    private final String grant_type;
    private final String redirect_uri;
}
