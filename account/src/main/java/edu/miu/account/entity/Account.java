package edu.miu.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Encrypted;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private String id;

    private String firstName;

    private String lastName;
    @Indexed(unique = true)
    private String email;

    @Encrypted
    private String password;

    private List<Role> roles;
    private Address address;

}
