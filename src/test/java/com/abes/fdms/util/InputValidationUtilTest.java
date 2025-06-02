package com.abes.fdms.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link InputValidationUtil}.
 * Tests validation of email, phone number, name, and password.
 */
class InputValidationUtilTest {

    /**
     * Tests valid and invalid email formats.
     */
    @Test
    void testValidEmail() {
        assertTrue(InputValidationUtil.isValidEmail("test@example.com"));
        assertFalse(InputValidationUtil.isValidEmail("invalid-email"));
        assertFalse(InputValidationUtil.isValidEmail("test@.com"));
        assertFalse(InputValidationUtil.isValidEmail(null));
    }

    /**
     * Tests valid and invalid phone number formats.
     */
    @Test
    void testValidPhoneNumber() {
        assertTrue(InputValidationUtil.isValidPhoneNumber("1234567890"));
        assertFalse(InputValidationUtil.isValidPhoneNumber("12345"));
        assertFalse(InputValidationUtil.isValidPhoneNumber("abcdefghij"));
        assertFalse(InputValidationUtil.isValidPhoneNumber(null));
    }

    /**
     * Tests valid and invalid name formats.
     */
    @Test
    void testValidName() {
        assertTrue(InputValidationUtil.isValidName("John Doe"));
        assertFalse(InputValidationUtil.isValidName("12345"));
        assertFalse(InputValidationUtil.isValidName(""));
        assertFalse(InputValidationUtil.isValidName(null));
        assertFalse(InputValidationUtil.isValidName("   "));
    }

    /**
     * Tests valid and invalid password formats.
     */
    @Test
    void testValidPassword() {
        assertTrue(InputValidationUtil.isValidPassword("Abc@123"));
        assertFalse(InputValidationUtil.isValidPassword("abc123")); // no uppercase, no special
        assertFalse(InputValidationUtil.isValidPassword("ABC@123")); // no lowercase
        assertFalse(InputValidationUtil.isValidPassword("Abcdefg")); // no digit, no special
        assertFalse(InputValidationUtil.isValidPassword("Abc@1"));   // too short
        assertFalse(InputValidationUtil.isValidPassword(null));
    }
}
