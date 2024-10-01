package com.publicissapient.ecommerce.service;

import com.publicissapient.ecommerce.entities.UserInfo;
import com.publicissapient.ecommerce.service.impl.UserInfoDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserInfoDetailsTest {

    private UserInfo userInfo;
    private UserInfoDetails userInfoDetails;

    @BeforeEach
    public void setup() {
        userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setEmail("test@example.com");
        userInfo.setPassword("password");

        // Assuming UserInfoDetails initializes the authorities from roles
        userInfoDetails = new UserInfoDetails(userInfo);
    }

    @Test
    public void testGetUserId() {
        assertEquals(userInfo.getUserId(), userInfoDetails.getUserId());
    }

    @Test
    public void testGetUsername() {
        assertEquals(userInfo.getEmail(), userInfoDetails.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals(userInfo.getPassword(), userInfoDetails.getPassword());
    }


    @Test
    public void testAccountNonExpired() {
        assertTrue(userInfoDetails.isAccountNonExpired());
    }

    @Test
    public void testAccountNonLocked() {
        assertTrue(userInfoDetails.isAccountNonLocked());
    }

    @Test
    public void testCredentialsNonExpired() {
        assertTrue(userInfoDetails.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        assertTrue(userInfoDetails.isEnabled());
    }
}

