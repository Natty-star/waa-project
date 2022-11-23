package edu.miu.gateway.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {
    public static final List<String> openApiEndpoints = List.of(
            "/api/v1/users/register",
            "/api/v1/authentication/authenticate",
            "/api/v1/authentication/validateUser",
            "/api/v1/authentication/authorizedUser",
            "/api/v1/property",
            "/api/v1/property/available",
            "/api/v1/property/nearby"
    );
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri) );
}
