package com.smartopd.queue_service.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtDecoderConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    JwtDecoder jwtDecoder() {

        byte[] keyBytes = Decoders.BASE64.decode(secret);

        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        return NimbusJwtDecoder
                .withSecretKey(key)
                .build();
    }
}