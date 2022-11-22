package edu.miu.account.dto;

import edu.miu.account.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private boolean status;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;
}
