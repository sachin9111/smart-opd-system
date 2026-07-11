package com.smartopd.api_gateway.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public boolean isTokenValid(String token) {

        try {

            extractAllClaims(token);

            return true;

        } catch (Exception e) {
        	
        	e.printStackTrace();

            return false;

        }

    }

    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();

    }

    public Date extractExpiration(String token) {

        return extractAllClaims(token).getExpiration();

    }

    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    private SecretKey getSigningKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secret);

        return Keys.hmacShaKeyFor(keyBytes);

    }
    
    public Long extractUserId(String token) {

        Claims claims = extractAllClaims(token);

        Object userId = claims.get("userId");

        if (userId instanceof Integer) {
            return ((Integer) userId).longValue();
        }

        if (userId instanceof Long) {
            return (Long) userId;
        }

        return Long.parseLong(userId.toString());
    }

    public String extractEmail(String token) {
        return extractUsername(token);
    }

    public String extractRoles(String token) {

        Claims claims = extractAllClaims(token);

        Object roles = claims.get("roles");

        return roles == null ? "" : roles.toString();
    }

}