package com.example.reactive.security;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public interface WebFilter {

    Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain);

}
