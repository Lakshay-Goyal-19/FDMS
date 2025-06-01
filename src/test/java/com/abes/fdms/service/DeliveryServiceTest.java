package com.abes.fdms.service;

import com.abes.fdms.dto.DeliveryPersonDto;
import com.abes.fdms.exception.DuplicateDeliveryPersonException;
import com.abes.fdms.util.CollectionUtil;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link DeliveryService}.
 * Tests delivery person registration, removal, status update, and availability.
 */
class DeliveryServiceTest {

    private DeliveryService deliveryService;

    /**
     * Sets up a clean state before each test.
     */
    @BeforeEach
    void setUp() {
        CollectionUtil.reset();
        deliveryService = new DeliveryService();
    }

    /**
     * Tests successful registration of a delivery person.
     */
    @Test
    void testRegisterDeliveryPerson_Success() throws Exception {
        deliveryService.registerDeliveryPerson("dp1", "Delivery One", "dp1@email.com", "9999999999");
        Map<String, DeliveryPersonDto> dps = deliveryService.getAllDeliveryPersons();
        assertTrue(dps.containsKey("dp1"));
        assertEquals("Delivery One", dps.get("dp1").getName());
    }

    /**
     * Tests registration fails for duplicate delivery person ID.
     */
    @Test
    void testRegisterDeliveryPerson_DuplicateId() throws Exception {
        deliveryService.registerDeliveryPerson("dp2", "Delivery Two", "dp2@email.com", "8888888888");
        assertThrows(DuplicateDeliveryPersonException.class, () -> {
            deliveryService.registerDeliveryPerson("dp2", "Another", "another@email.com", "7777777777");
        });
    }

    /**
     * Tests removal of a delivery person.
     */
    @Test
    void testRemoveDeliveryPerson() throws Exception {
        deliveryService.registerDeliveryPerson("dp3", "Delivery Three", "dp3@email.com", "6666666666");
        deliveryService.removeDeliveryPerson("dp3");
        assertNull(deliveryService.getAllDeliveryPersons().get("dp3"));
    }

    /**
     * Tests updating the availability status of a delivery person.
     */
    @Test
    void testUpdateStatus() throws Exception {
        deliveryService.registerDeliveryPerson("dp4", "Delivery Four", "dp4@email.com", "5555555555");
        deliveryService.updateStatus("dp4", false);
        assertFalse(deliveryService.getAllDeliveryPersons().get("dp4").isAvailable());
        deliveryService.updateStatus("dp4", true);
        assertTrue(deliveryService.getAllDeliveryPersons().get("dp4").isAvailable());
    }

    /**
     * Tests finding an available delivery person.
     */
    @Test
    void testGetAvailableDeliveryPerson() throws Exception {
        deliveryService.registerDeliveryPerson("dp5", "Delivery Five", "dp5@email.com", "4444444444");
        DeliveryPersonDto available = deliveryService.getAvailableDeliveryPerson();
        assertNotNull(available);
        assertEquals("dp5", available.getId());
        // Set unavailable and check again
        deliveryService.updateStatus("dp5", false);
        assertNull(deliveryService.getAvailableDeliveryPerson());
    }
}
