package com.smartopd.api_gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
    	
    	System.out.println("===== GATEWAY FILTER HIT =====");
        System.out.println(exchange.getRequest().getURI());

        String path = exchange.getRequest().getURI().getPath();

        // Public APIs
        if (path.startsWith("/api/v1/auth/")
                || path.startsWith("/actuator")) {

            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);
        
        System.out.println("Authorization = " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);
        System.out.println("Checking token...");
        
        boolean valid = jwtService.isTokenValid(token);

        System.out.println("Token valid = " + valid);

        if (!jwtService.isTokenValid(token)) {

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }
        
        System.out.println("Token accepted");

        // Extract data from JWT
        Long userId = jwtService.extractUserId(token);
        System.out.println("UserId = " + userId);
        String email = jwtService.extractEmail(token);
        System.out.println("Email = " + email);
        String roles = jwtService.extractRoles(token);
        
        System.out.println("Roles = " + roles);

        // Add user information as request headers
        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(exchange.getRequest()
                        .mutate()
                        .header("X-User-Id", String.valueOf(userId))
                        .header("X-User-Email", email)
                        .header("X-User-Roles", roles)
                        .build())
                .build();

        return chain.filter(mutatedExchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}