package com.app.ecommerce.api_gateway.filters;

import com.app.ecommerce.api_gateway.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {

    @Autowired
    private JwtService jwtService;

    public AuthenticationGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String token = exchange.getRequest().getHeaders().getFirst("Authorization");

                // Check if the token is null or doesn't start with "Bearer "
                if (token == null || !token.startsWith("Bearer ")) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                // Extract token
                String jwtToken = token.substring(7);

                // Validate token
                String userId = jwtService.extractUsername(jwtToken);

                if (userId == null || !jwtService.isValidToken(jwtToken)) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                // Get roles from the token
                String role = jwtService.extractRoles(jwtToken);

                // Check if the user's roles are allowed for the requested route
                List<String> allowedRoles = config.getAllowedRoles();
                if (!allowedRoles.contains(role)) {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }

                // Pass roles and userId as headers for downstream services
                ServerHttpRequest mutatedRequest =  exchange.getRequest()
                        .mutate()
                        .header("X-UserId", userId)
                        .header("X-Roles",  role)
                        .build();
                ServerWebExchange mutatedExchange = exchange
                        .mutate()
                        .request(mutatedRequest)
                        .build();
                return chain.filter(mutatedExchange);
            }
        };
    }

    public static class Config {
        private List<String> allowedRoles;

        // Getter and Setter for allowedRoles
        public List<String> getAllowedRoles() {
            return allowedRoles;
        }

        public void setAllowedRoles(List<String> allowedRoles) {
            this.allowedRoles = allowedRoles;
        }
    }
}
