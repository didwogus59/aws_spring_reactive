package com.example.reactive.jwt;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@Order(3)
public class jwtFilter implements WebFilter {

    @Autowired
    private jwtProvider provider;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = resolveToken(exchange);
        log.info("token: {}", token);
        if (token != null && provider.validateToken(token)) {
            String name = provider.getAccount(token);
            exchange.getAttributes().put("username", name);
        }
        else {
            exchange.getAttributes().put("username", "null");
        }

        return chain.filter(exchange);
    }

    private String resolveToken(ServerWebExchange exchange) {
        HttpCookie cookie = exchange.getRequest().getCookies().getFirst("jwt");
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }
}
