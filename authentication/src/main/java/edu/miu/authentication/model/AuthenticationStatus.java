package edu.miu.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationStatus {
    private Boolean isAuthenticated;
    private String message;
    private AuthResponse data;
}
