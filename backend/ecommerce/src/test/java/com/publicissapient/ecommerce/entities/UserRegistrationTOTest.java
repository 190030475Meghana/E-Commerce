package com.publicissapient.ecommerce.entities;


import com.publicissapient.ecommerce.dto.UserRegistrationTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRegistrationTOTest {

    @Test
    public void testGettersAndSetters() {

        UserRegistrationTO userRegistrationTO = new UserRegistrationTO();


        userRegistrationTO.setName("John Doe");
        userRegistrationTO.setEmail("john.doe@example.com");
        userRegistrationTO.setPassword("password");
        userRegistrationTO.setStreet("123 Main St");
        userRegistrationTO.setCity("Springfield");
        userRegistrationTO.setZipCode("12345");


        assertEquals("John Doe", userRegistrationTO.getName());
        assertEquals("john.doe@example.com", userRegistrationTO.getEmail());
        assertEquals("password", userRegistrationTO.getPassword());
        assertEquals("123 Main St", userRegistrationTO.getStreet());
        assertEquals("Springfield", userRegistrationTO.getCity());
        assertEquals("12345", userRegistrationTO.getZipCode());
    }


}

