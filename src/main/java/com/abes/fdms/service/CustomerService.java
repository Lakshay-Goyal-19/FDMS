package com.abes.fdms.service;

import java.util.Map;

import com.abes.fdms.dao.UserDao;
import com.abes.fdms.dao.UserDaoImpl;
import com.abes.fdms.dto.CustomerDto;

/**
 * Service class for customer-related operations such as registration and login.
 */
public class CustomerService {
    private final UserDao userDao = new UserDaoImpl();

    /**
     * Registers a new customer if the ID, email, and phone number are unique.
     *
     * @param id Customer ID
     * @param name Customer name
     * @param email Customer email
     * @param phoneNumber Customer phone number
     * @param password Customer password
     * @return true if registration is successful, false if duplicate found
     */
    public boolean registerCustomer(String id, String name, String email, String phoneNumber, String password) {
        // Check for duplicate ID
        if (userDao.getCustomers().containsKey(id)) {
            return false;
        }
        // Check for duplicate email or phone
        for (CustomerDto c : userDao.getCustomers().values()) {
            if (c.getEmail().equalsIgnoreCase(email) || c.getPhoneNumber().equals(phoneNumber)) {
                return false;
            }
        }
        userDao.addCustomer(new CustomerDto(id, name, email, phoneNumber, password));
        return true;
    }

    /**
     * Authenticates a customer using ID and password.
     *
     * @param id Customer ID
     * @param password Customer password
     * @return CustomerDto if credentials are valid, null otherwise
     */
    public CustomerDto loginCustomer(String id, String password) {
        CustomerDto customer = userDao.getCustomerById(id);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        return null;
    }

    /**
     * Retrieves all registered customers.
     *
     * @return Map of customer ID to CustomerDto
     */
    public Map<String, CustomerDto> getAllCustomers() {
        return userDao.getCustomers();
    }
}
