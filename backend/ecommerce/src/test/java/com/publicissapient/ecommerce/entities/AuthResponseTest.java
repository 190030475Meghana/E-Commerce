package com.publicissapient.ecommerce.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthResponseTest {

    @Test
    public void testConstructorAndGetters() {
        String token = "abc123xyz";
        String username = "testuser";
        Long userId = 1L;

        AuthResponse authResponse = new AuthResponse(token, username, userId);

        assertEquals(token, authResponse.getToken());
        assertEquals(username, authResponse.getUsername());
        assertEquals(userId, authResponse.getUserId());
    }

    @Test
    public void testSetters() {
        AuthResponse authResponse = new AuthResponse(null, null, null);

        String token = "newToken";
        String username = "newUsername";
        Long userId = 2L;

        authResponse.setToken(token);
        authResponse.setUsername(username);
        authResponse.setUserId(userId);

        assertEquals(token, authResponse.getToken());
        assertEquals(username, authResponse.getUsername());
        assertEquals(userId, authResponse.getUserId());
    }
}
