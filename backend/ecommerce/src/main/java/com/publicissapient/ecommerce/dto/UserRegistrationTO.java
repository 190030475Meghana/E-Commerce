package com.publicissapient.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationTO {
    private String name;
    private String email;
    private String password;
    private String street;
    private String city;
    private String zipCode;
}
