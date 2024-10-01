package com.publicissapient.ecommerce.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthRequestTest {

    @Test
    public void testSettersAndGetters() {
        AuthRequest authRequest = new AuthRequest();

        String username = "newusername";
        String password = "newpassword";

        authRequest.setUsername(username);
        authRequest.setPassword(password);

        assertEquals(username, authRequest.getUsername());
        assertEquals(password, authRequest.getPassword());
    }

    @Test
    public void testAllArgsConstructor() {
        String username = "testuser";
        String password = "testpassword";

        AuthRequest authRequest = new AuthRequest(username, password);

        assertEquals(username, authRequest.getUsername());
        assertEquals(password, authRequest.getPassword());
    }
}
