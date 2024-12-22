package com.codigo.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ExceptionHandlingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange)
                .onErrorResume(throwable -> {
                    if (throwable instanceof ResponseStatusException) {
                        ResponseStatusException ex = (ResponseStatusException) throwable;
                        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno en el API Gateway", ex));
                    }
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la solicitud"));
                });
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
