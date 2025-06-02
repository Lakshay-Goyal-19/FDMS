package com.abes.fdms.service;

import com.abes.fdms.dao.DeliveryPersonDao;
import com.abes.fdms.dao.DeliveryPersonDaoImpl;
import com.abes.fdms.dto.DeliveryPersonDto;
import com.abes.fdms.exception.DuplicateDeliveryPersonException;
import java.util.Map;

/**
 * Service class for managing delivery persons.
 */
public class DeliveryService {
    private final DeliveryPersonDao deliveryPersonDao = new DeliveryPersonDaoImpl();

    /**
     * Registers a new delivery person if the ID is unique.
     *
     * @param id Delivery person ID
     * @param name Name
     * @param email Email
     * @param phone Phone number
     * @throws DuplicateDeliveryPersonException if ID already exists
     */
    public void registerDeliveryPerson(String id, String name, String email, String phone) throws DuplicateDeliveryPersonException {
        if (deliveryPersonDao.getDeliveryPersons().containsKey(id)) {
            throw new DuplicateDeliveryPersonException(id);
        }
        DeliveryPersonDto dp = new DeliveryPersonDto(id, name, email, phone);
        deliveryPersonDao.addDeliveryPerson(dp);
    }

    /**
     * Removes a delivery person by ID.
     *
     * @param id Delivery person ID
     */
    public void removeDeliveryPerson(String id) {
        deliveryPersonDao.removeDeliveryPerson(id);
    }

    /**
     * Updates the availability status of a delivery person.
     *
     * @param id Delivery person ID
     * @param available Availability status
     */
    public void updateStatus(String id, boolean available) {
        DeliveryPersonDto dp = deliveryPersonDao.getDeliveryPersonById(id);
        if (dp != null) {
            dp.setAvailable(available);
        }
    }

    /**
     * Retrieves all delivery persons.
     *
     * @return Map of delivery person ID to DeliveryPersonDto
     */
    public Map<String, DeliveryPersonDto> getAllDeliveryPersons() {
        return deliveryPersonDao.getDeliveryPersons();
    }

    /**
     * Finds and returns an available delivery person.
     *
     * @return Available DeliveryPersonDto, or null if none available
     */
    public DeliveryPersonDto getAvailableDeliveryPerson() {
        for (DeliveryPersonDto dp : deliveryPersonDao.getDeliveryPersons().values()) {
            if (dp.isAvailable()) {
                return dp;
            }
        }
        return null;
    }
}
