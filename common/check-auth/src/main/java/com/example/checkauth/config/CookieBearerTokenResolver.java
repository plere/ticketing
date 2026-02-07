package com.example.checkauth.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;

import java.util.Arrays;

public class CookieBearerTokenResolver implements BearerTokenResolver {

    @Override
    public String resolve(HttpServletRequest request) {
        return getCookieValue(request.getHeader(HttpHeaders.COOKIE), "accessToken");
    }

    private String getCookieValue(String cookies, String name) {
        return Arrays.stream(cookies.split(";"))
            .filter(cookie -> cookie.split("=")[0].trim().equals(name))
            .findFirst()
            .map(cookie -> cookie.split("=")[1].trim())
            .orElse(null);
    }
}
