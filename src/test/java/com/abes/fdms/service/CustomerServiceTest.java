package com.abes.fdms.service;

import com.abes.fdms.dto.CustomerDto;
import com.abes.fdms.util.CollectionUtil;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link CustomerService}.
 * Tests customer registration and login functionality.
 */
class CustomerServiceTest {

    private CustomerService customerService;

    /**
     * Sets up a clean state before each test.
     */
    @BeforeEach
    void setUp() {
        CollectionUtil.reset(); // Ensure clean state before each test
        customerService = new CustomerService();
    }

    /**
     * Tests successful customer registration.
     */
    @Test
    void testRegisterCustomer_Success() {
        boolean result = customerService.registerCustomer("test1", "Test User", "test1@email.com", "1234567891", "Pass@123");
        assertTrue(result);
        CustomerDto customer = customerService.loginCustomer("test1", "Pass@123");
        assertNotNull(customer);
        assertEquals("test1", customer.getId());
    }

    /**
     * Tests registration fails for duplicate customer ID.
     */
    @Test
    void testRegisterCustomer_DuplicateId() {
        customerService.registerCustomer("dup1", "User1", "dup1@email.com", "1234567892", "Pass@123");
        boolean result = customerService.registerCustomer("dup1", "User2", "dup2@email.com", "1234567893", "Pass@123");
        assertFalse(result);
    }

    /**
     * Tests registration fails for duplicate email or phone number.
     */
    @Test
    void testRegisterCustomer_DuplicateEmailOrPhone() {
        customerService.registerCustomer("unique1", "User1", "unique@email.com", "1234567894", "Pass@123");
        boolean result = customerService.registerCustomer("unique2", "User2", "unique@email.com", "1234567894", "Pass@123");
        assertFalse(result);
    }

    /**
     * Tests successful customer login.
     */
    @Test
    void testLoginCustomer_Success() {
        customerService.registerCustomer("login1", "User1", "login1@email.com", "1234567895", "Pass@123");
        CustomerDto customer = customerService.loginCustomer("login1", "Pass@123");
        assertNotNull(customer);
        assertEquals("login1", customer.getId());
    }

    /**
     * Tests login fails with wrong password.
     */
    @Test
    void testLoginCustomer_Failure() {
        customerService.registerCustomer("login2", "User2", "login2@email.com", "1234567896", "Pass@123");
        CustomerDto customer = customerService.loginCustomer("login2", "WrongPass");
        assertNull(customer);
    }
}
