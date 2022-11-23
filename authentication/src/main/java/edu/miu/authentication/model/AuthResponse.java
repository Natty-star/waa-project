package edu.miu.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Boolean status;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;

}
