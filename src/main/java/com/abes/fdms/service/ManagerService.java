package com.abes.fdms.service;

import java.util.Map;

import com.abes.fdms.dao.FoodItemDao;
import com.abes.fdms.dao.FoodItemDaoImpl;
import com.abes.fdms.dao.UserDao;
import com.abes.fdms.dao.UserDaoImpl;
import com.abes.fdms.dto.FoodItemDto;
import com.abes.fdms.dto.ManagerDto;

/**
 * Service class for manager authentication and (optionally) manager operations.
 */
public class ManagerService {
    private final UserDao userDao = new UserDaoImpl();

    /**
     * Authenticates a manager using ID and password.
     *
     * @param id Manager ID
     * @param password Manager password
     * @return ManagerDto if credentials are valid, null otherwise
     */
    public ManagerDto loginManager(String id, String password) {
        ManagerDto manager = userDao.getManagerById(id);
        if (manager != null && manager.getPassword().equals(password)) {
            return manager;
        }
        return null;
    }
}
