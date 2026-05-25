package com.example.userservice.external.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@ConfigurationProperties("external.client.auth")
public record AuthProperty(
    String domain,
    String uri,
    String authorize_path,
    String response_type,
    String client_id,
    String client_secret,
    String grant_type,
    String redirect_uri,
    List<String> scope
) {
    public String getOAuthLoginUri(String state) {
        return uri + authorize_path
            + "?response_type=" + response_type
            + "&client_id=" + client_id
            + "&redirect_uri=" + redirect_uri
            + "&scope=" + String.join(URLEncoder.encode(" ", StandardCharsets.UTF_8), scope)
            + "&state=" + state;
    }
}
