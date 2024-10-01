package com.publicissapient.ecommerce.service;

import com.publicissapient.ecommerce.service.impl.EmailValidator;
import com.publicissapient.ecommerce.service.impl.PasswordValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @Test
    public void testValidEmails() {
        assertTrue(EmailValidator.isValidEmail("john.doe@example.com"));
        assertTrue(EmailValidator.isValidEmail("jane_doe123@example.co.uk"));
        assertTrue(EmailValidator.isValidEmail("info@sub.domain.com"));
        assertTrue(EmailValidator.isValidEmail("user@example-domain.com"));
        assertTrue(EmailValidator.isValidEmail("first.last@domain-name.com"));
    }

    @Test
    public void testInvalidEmails() {
        assertFalse(EmailValidator.isValidEmail("john.doe@example")); // Missing top-level domain
        assertFalse(EmailValidator.isValidEmail("jane@doe@domain.com")); // Double @ sign
        assertFalse(EmailValidator.isValidEmail("info@sub..domain.com")); // Double dots
        assertFalse(EmailValidator.isValidEmail("user@domain_name.com")); // Underscore in domain name
        assertFalse(EmailValidator.isValidEmail("invalid-email")); // Missing @ and domain
    }

    @Test
    public void testNullEmail() {
        assertFalse(EmailValidator.isValidEmail(null));
    }

    @Test
    public void testEmptyEmail() {
        assertFalse(EmailValidator.isValidEmail(""));
    }

    @Test
    public void testValidPasswords() {
        assertTrue(PasswordValidator.isValidPassword("Password1@"));
        assertTrue(PasswordValidator.isValidPassword("P@ssw0rd!"));
        assertTrue(PasswordValidator.isValidPassword("SecurePwd12@"));
        assertTrue(PasswordValidator.isValidPassword("ValidPwd1$"));
    }

    @Test
    public void testInvalidPasswords() {
        assertFalse(PasswordValidator.isValidPassword("password")); // Missing uppercase and special character
        assertFalse(PasswordValidator.isValidPassword("Password")); // Missing digit and special character
        assertFalse(PasswordValidator.isValidPassword("password1")); // Missing uppercase and special character
        assertFalse(PasswordValidator.isValidPassword("Password@")); // Missing digit
        assertFalse(PasswordValidator.isValidPassword("Password123")); // Missing special character
        assertFalse(PasswordValidator.isValidPassword("P@ss")); // Less than 8 characters
        assertFalse(PasswordValidator.isValidPassword("VeryLongPassword123456789")); // More than 16 characters
        assertFalse(PasswordValidator.isValidPassword("")); // Null password
    }
}

