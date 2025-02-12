package com.ecommerce.controller;

import com.ecommerce.dto.UserRegistrationTO;
import com.ecommerce.entities.Address;
import com.ecommerce.entities.UserInfo;
import com.ecommerce.service.impl.UserInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTests {
    @Mock
    private UserInfoService userInfoService;

    @InjectMocks
    private UserController userController;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddNewUser() {
        // Mock userInfoService.addUser() method
        when(userInfoService.addUser(any(UserInfo.class), any(Address.class))).thenReturn("User added successfully");

        // Create a sample request DTO
        UserRegistrationTO userRegistrationTO = new UserRegistrationTO();
        userRegistrationTO.setName("John Doe");
        userRegistrationTO.setEmail("john.doe@example.com");
        userRegistrationTO.setPassword("password");
        userRegistrationTO.setStreet("123 Main St");
        userRegistrationTO.setCity("New York");
        userRegistrationTO.setZipCode("10001");

        // Call the controller method directly
        String result = userController.addNewUser(userRegistrationTO);

        // Assert the result
        assertEquals("User added successfully", result);
    }


}
