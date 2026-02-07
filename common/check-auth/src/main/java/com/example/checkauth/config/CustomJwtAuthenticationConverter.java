package com.example.checkauth.config;

import com.example.checkauth.UserToken;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, UserToken> {
    @Override
    public UserToken convert(Jwt jwt) {
        Long id = Long.valueOf(jwt.getClaimAsString("id"));
        String userId = jwt.getClaimAsString("sub");
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

        // 🔑 JwtAuthenticationToken 대신 CustomAuthenticationToken 반환
        return new UserToken(id, userId, authorities);
    }
}
