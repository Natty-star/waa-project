package edu.miu.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {
    private String country;

    private String state;

    private String city;

    private String zipcode;

    private String street;
}
