package com.abes.fdms.service;

import com.abes.fdms.dao.DeliveryPersonDao;
import com.abes.fdms.dao.DeliveryPersonDaoImpl;
import com.abes.fdms.dto.DeliveryPersonDto;
import java.util.Map;

public class DeliveryService {
    private final DeliveryPersonDao deliveryPersonDao = new DeliveryPersonDaoImpl();

    // Modified: Now takes email and phone, and checks for duplicate ID
    public boolean registerDeliveryPerson(String id, String name, String email, String phone) {
        if (deliveryPersonDao.getDeliveryPersons().containsKey(id)) {
            return false;
        }
        DeliveryPersonDto dp = new DeliveryPersonDto(id, name, email, phone);
        deliveryPersonDao.addDeliveryPerson(dp);
        return true;
    }

    public void removeDeliveryPerson(String id) {
        deliveryPersonDao.removeDeliveryPerson(id);
    }

    public void updateStatus(String id, boolean available) {
        DeliveryPersonDto dp = deliveryPersonDao.getDeliveryPersonById(id);
        if (dp != null) {
            dp.setAvailable(available);
        }
    }

    public Map<String, DeliveryPersonDto> getAllDeliveryPersons() {
        return deliveryPersonDao.getDeliveryPersons();
    }

    public DeliveryPersonDto getAvailableDeliveryPerson() {
        for (DeliveryPersonDto dp : deliveryPersonDao.getDeliveryPersons().values()) {
            if (dp.isAvailable()) {
                return dp;
            }
        }
        return null;
    }
}
