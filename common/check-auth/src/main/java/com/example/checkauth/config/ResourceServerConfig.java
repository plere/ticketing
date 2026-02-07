package com.example.checkauth.config;

import com.example.checkauth.UserToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;


@Configuration
public class ResourceServerConfig {
    @Bean
    public Converter<Jwt, UserToken> customJwtAuthenticationConverter() {
        return new CustomJwtAuthenticationConverter();
    }

    @Bean
    public BearerTokenResolver cookieBearerTokenResolver() {
        return new CookieBearerTokenResolver();
    }

    @Bean
    public JwtDecoder jwtDecoder(JwtKeyProvider keyProvider) {
        return NimbusJwtDecoder
            .withJwkSetUri(keyProvider.jwkSetUri())
            .build();
    }
}
