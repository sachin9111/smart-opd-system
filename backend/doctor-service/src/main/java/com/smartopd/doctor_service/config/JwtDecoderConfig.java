package com.smartopd.doctor_service.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

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
    
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter converter =
                new JwtGrantedAuthoritiesConverter();


        converter.setAuthoritiesClaimName("roles");


        converter.setAuthorityPrefix("");


        JwtAuthenticationConverter jwtConverter =
                new JwtAuthenticationConverter();


        jwtConverter.setJwtGrantedAuthoritiesConverter(
                converter
        );


        return jwtConverter;
    }
}