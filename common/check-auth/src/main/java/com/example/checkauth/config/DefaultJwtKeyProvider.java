package com.example.checkauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultJwtKeyProvider implements JwtKeyProvider {
    @Value("${oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Override
    public String jwkSetUri() {
        return jwkSetUri;
    }
}
