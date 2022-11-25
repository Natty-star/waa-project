package edu.miu.gateway.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class AdminRouterValidator {
    public static final List<String>  openApiEndpoints = List.of(
            "/api/v1/users/di-active",
            "/api/v1/users/register"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .anyMatch(uri -> request.getURI().getPath().contains(uri));
}
