package edu.miu.gateway.security;

import edu.miu.gateway.model.AccessToken;
import edu.miu.gateway.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.server.reactive.ServerHttpRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RefreshScope
@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private final RouterValidator routerValidator;
    @Autowired
    private RestTemplate restTemplate;

    public AuthenticationFilter(RouterValidator routerValidator) {
        super(Config.class);
        this.routerValidator = routerValidator;
    }

    @Override
    public GatewayFilter apply(Config config){
        return ((exchange, chain) -> {

            String authHeader = "";

            if (routerValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    List<String> details = new ArrayList<>();
                    details.add("Missing Authorization Header");
                    log.error(details.toString());
                    return getMono(exchange, details);
                }

                authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);

                try {
                    AccessToken request = new AccessToken(authHeader);
                    System.out.println(request);
                    restTemplate.postForObject("http://localhost:8081/api/v1/authentication/validateUser", request, Object.class);
                }
                catch (Exception ex) {
                    log.error("Error Validating Authentication Header", ex);
                    List<String> details = new ArrayList<>();
                    details.add(ex.getLocalizedMessage());
                    return getMono(exchange, details);
                }
            }

            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("jwt", getToken(authHeader))
                    .build();

            exchange.mutate().request(request).build();

            return chain.filter(exchange);
        });
    }

    private Mono<Void> getMono(ServerWebExchange exchange, List<String> details) {
        ErrorResponse error = new ErrorResponse(new Date(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", details, exchange.getRequest().getURI().toString());

        byte[] bytes = error.toString().getBytes(StandardCharsets.UTF_8);

        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        exchange.getResponse().getHeaders().add("Content-Type", "application/json");
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

        return exchange.getResponse().writeWith(Flux.just(buffer));
    }

    public static class Config {
    }

    public String getToken(String header){

        String[] parts = header.split(" ");
        if (parts.length == 2 && "Bearer".equals(parts[0])) {
            return parts[1];
        }
        return "";
    }
}
