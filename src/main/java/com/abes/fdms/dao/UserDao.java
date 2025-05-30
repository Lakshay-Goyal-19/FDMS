package com.abes.fdms.dao;

import com.abes.fdms.dto.CustomerDto;
import com.abes.fdms.dto.ManagerDto;
import java.util.Map;

public interface UserDao {
    Map<String, CustomerDto> getCustomers();
    Map<String, ManagerDto> getManagers();
    void addCustomer(CustomerDto customer);
    void addManager(ManagerDto manager);
    CustomerDto getCustomerById(String id);
    ManagerDto getManagerById(String id);
}
