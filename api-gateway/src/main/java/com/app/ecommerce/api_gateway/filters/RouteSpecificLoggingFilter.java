package com.app.ecommerce.api_gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RouteSpecificLoggingFilter extends AbstractGatewayFilterFactory<RouteSpecificLoggingFilter.Config> {

    public RouteSpecificLoggingFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("order filter pre {}",exchange.getRequest().getURI());

            return chain.filter(exchange);
        };
    }



    public static class Config {

    }
}
