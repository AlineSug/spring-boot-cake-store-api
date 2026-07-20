package com.cakes.store.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptPasswords {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String criptografia(String password){
        return encoder.encode(password);
    }
}
