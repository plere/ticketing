package com.example.checkauth;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class UserToken extends AbstractAuthenticationToken {
    private final String id;
    private final String email;

    public UserToken(String email, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.id = "email.toLowerCase();";
        this.email = email;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null; // JWT 기반이라 자격증명은 없음
    }

    @Override
    public Object getPrincipal() {
        return email;
    }
}
