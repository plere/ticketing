package com.example.userservice.external.auth;

import com.example.userservice.external.auth.config.FeignLoggingConfig;
import com.example.userservice.external.auth.dto.LoginResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "authFeignClient", url = "${external.client.auth.uri}", configuration = FeignLoggingConfig.class)


public interface AuthClient {
    @PostMapping(value = "/oauth2/token", consumes = "application/x-www-form-urlencoded")
    LoginResponseDto login(@RequestHeader("Authorization") String authorization,
                           @RequestBody Map<String, ?> form
    );
}