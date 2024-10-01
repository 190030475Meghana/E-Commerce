package com.publicissapient.ecommerce.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInfoTests {

    @Test
    void testUserInfoGettersAndSetters() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setEmail("test@example.com");
        userInfo.setName("John Doe");
        userInfo.setPassword("password");

        Address address = new Address();
        address.setAddressId(1L);
        address.setStreet("123 Main St");
        address.setCity("Anytown");
        address.setZipCode("12345");

        userInfo.setAddress(address);

        assertEquals(1L, userInfo.getUserId());
        assertEquals("test@example.com", userInfo.getEmail());
        assertEquals("John Doe", userInfo.getName());
        assertEquals("password", userInfo.getPassword());
        assertEquals(address, userInfo.getAddress());
    }

    @Test
    void testAddressGettersAndSetters() {
        Address address = new Address();
        address.setAddressId(1L);
        address.setStreet("123 Main St");
        address.setCity("Anytown");
        address.setZipCode("12345");

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setEmail("test@example.com");
        userInfo.setName("John Doe");
        userInfo.setPassword("password");
        address.setUserInfo(userInfo);

        assertEquals(1L, address.getAddressId());
        assertEquals("123 Main St", address.getStreet());
        assertEquals("Anytown", address.getCity());
        assertEquals("12345", address.getZipCode());
        assertEquals(userInfo, address.getUserInfo());
    }


    @Test
    public void testAllArgsConstructor() {
        Long addressId = 1L;
        String street = "123 Main St";
        String city = "New York";
        String zipCode = "10001";
        UserInfo userInfo = new UserInfo();
        Address address = new Address(addressId, street, city, zipCode, userInfo);

        assertEquals(addressId, address.getAddressId());
        assertEquals(street, address.getStreet());
        assertEquals(city, address.getCity());
        assertEquals(zipCode, address.getZipCode());
        assertEquals(userInfo, address.getUserInfo());
    }


}
