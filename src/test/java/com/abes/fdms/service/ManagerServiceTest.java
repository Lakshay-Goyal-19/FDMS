package com.abes.fdms.service;

import com.abes.fdms.dto.ManagerDto;
import com.abes.fdms.util.CollectionUtil;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link ManagerService}.
 * Tests manager login functionality.
 */
class ManagerServiceTest {

    private ManagerService managerService;

    /**
     * Sets up a clean state and adds a dummy manager before each test.
     */
    @BeforeEach
    void setUp() {
        CollectionUtil.reset();
        // Add a dummy manager for login tests
        CollectionUtil.managers.put("m1", new ManagerDto("m1", "Manager One", "m1@email.com", "1111111111", "Pass@123"));
        managerService = new ManagerService();
    }

    /**
     * Tests successful manager login.
     */
    @Test
    void testLoginManager_Success() {
        ManagerDto manager = managerService.loginManager("m1", "Pass@123");
        assertNotNull(manager);
        assertEquals("m1", manager.getId());
    }

    /**
     * Tests login fails with wrong password.
     */
    @Test
    void testLoginManager_WrongPassword() {
        ManagerDto manager = managerService.loginManager("m1", "WrongPass");
        assertNull(manager);
    }

    /**
     * Tests login fails for non-existent manager ID.
     */
    @Test
    void testLoginManager_NonExistentId() {
        ManagerDto manager = managerService.loginManager("notfound", "Pass@123");
        assertNull(manager);
    }
}
