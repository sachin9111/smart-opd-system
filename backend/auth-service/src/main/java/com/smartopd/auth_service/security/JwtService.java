package com.smartopd.auth_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.smartopd.auth_service.entity.User;
import com.smartopd.auth_service.entity.UserRole;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    public String generateAccessToken(String username) {
        return generateToken(username, accessTokenExpiration, new HashMap<>());
    }

    public String generateRefreshToken(String username) {
        return generateToken(username, refreshTokenExpiration, new HashMap<>());
    }

    public String generateAccessToken(String username,
                                      Map<String, Object> claims) {
        return generateToken(username, accessTokenExpiration, claims);
    }

    private String generateToken(String username,
                                 long expiration,
                                 Map<String, Object> claims) {

        Date now = new Date();

        Date expiry = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token,
                                String username) {

        String extractedUsername = extractUsername(token);

        return extractedUsername.equals(username)
                && !isTokenExpired(token);
    }

    public <T> T extractClaim(String token,
                              Function<Claims, T> resolver) {

        Claims claims = extractAllClaims(token);

        return resolver.apply(claims);
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
    
    
    public String generateAccessToken(User user) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", user.getId());

        claims.put(
                "roles",
                user.getUserRoles()
                        .stream()
                        .filter(UserRole::isActive)
                        .map(userRole -> userRole.getRole().getCode().name())
                        .toList());

        return generateToken(
                user.getEmail(),
                accessTokenExpiration,
                claims);

    }
    
    public long getAccessTokenExpirationSeconds() {
        return accessTokenExpiration / 1000;
    }

    public long getRefreshTokenExpirationSeconds() {
        return refreshTokenExpiration / 1000;
    }

}