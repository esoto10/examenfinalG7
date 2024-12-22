package com.codigo.apigateway.config;

import com.codigo.apigateway.filter.ExceptionHandlingFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public GlobalFilter exceptionHandlingFilter() {
        return new ExceptionHandlingFilter(); // Registrar el filtro globalmente
    }
}
