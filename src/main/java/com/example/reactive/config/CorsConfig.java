package com.example.reactive.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


@Configuration
@Slf4j
@Order(1)
public class CorsConfig {

    private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN";
    private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS, jwt";
    private static final String ALLOWED_ORIGIN = "*";
    private static final String MAX_AGE = "3600";


    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();

            // 허용할 도메인 리스트
            List<String> allowedOrigins = Arrays.asList("http://localhost:8080","http://localhost:63342", "http://3.36.90.253:8080", "https://localhost:8080","https://localhost:63342", "https://3.36.90.253:8080");
            String originHeader = request.getHeaders().getOrigin();

            if (CorsUtils.isCorsRequest(request)) {
                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders headers = response.getHeaders();
                log.info(originHeader);
                // 요청의 Origin 헤더가 허용된 도메인 리스트에 있는지 확인
                if (allowedOrigins.contains(originHeader)) {
                    headers.add("Access-Control-Allow-Origin", originHeader); // 요청된 도메인만 허용
                    headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
                    headers.add("Access-Control-Max-Age", MAX_AGE);
                    headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
                    headers.add("Access-Control-Allow-Credentials", "true");
                }

                // OPTIONS 요청일 경우, 미리 응답 처리
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }

            return chain.filter(ctx);
        };
    }

}