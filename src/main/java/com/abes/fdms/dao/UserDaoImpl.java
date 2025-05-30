package com.abes.fdms.dao;

import com.abes.fdms.dto.CustomerDto;
import com.abes.fdms.dto.ManagerDto;
import com.abes.fdms.util.CollectionUtil;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    @Override
    public Map<String, CustomerDto> getCustomers() {
        return CollectionUtil.customers;
    }

    @Override
    public Map<String, ManagerDto> getManagers() {
        return CollectionUtil.managers;
    }

    @Override
    public void addCustomer(CustomerDto customer) {
        CollectionUtil.customers.put(customer.getId(), customer);
    }

    @Override
    public void addManager(ManagerDto manager) {
        CollectionUtil.managers.put(manager.getId(), manager);
    }

    @Override
    public CustomerDto getCustomerById(String id) {
        return CollectionUtil.customers.get(id);
    }

    @Override
    public ManagerDto getManagerById(String id) {
        return CollectionUtil.managers.get(id);
    }
}
