package com.example.user.service;


import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class PasswordEncryptorImpl implements PasswordEncryptor {
    
    @Override
    public String encrypt(String password) {
        return Hashing.sha256()
            .hashString(password, StandardCharsets.UTF_8)
            .toString();
    }
}
