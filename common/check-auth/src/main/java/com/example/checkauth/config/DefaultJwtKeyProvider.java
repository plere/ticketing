package com.example.checkauth.config;

import org.springframework.stereotype.Component;

@Component
public class DefaultJwtKeyProvider implements JwtKeyProvider {

    @Override
    public String jwkSetUri() {
        return "https://mock-ticketing.com:8084/oauth2/jwks";
    }
}
