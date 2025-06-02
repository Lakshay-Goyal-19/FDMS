package com.abes.fdms.service;

import com.abes.fdms.dao.FoodItemDao;
import com.abes.fdms.dao.FoodItemDaoImpl;
import com.abes.fdms.dto.FoodItemDto;
import com.abes.fdms.exception.DuplicateItemException;

import java.util.Map;

/**
 * Service class for managing food items and inventory.
 */
public class FoodService {
    private final FoodItemDao foodItemDao = new FoodItemDaoImpl();

    /**
     * Adds a new food item to the menu if it does not already exist.
     *
     * @param name Item name
     * @param price Item price
     * @param quantity Initial quantity
     * @throws DuplicateItemException if item already exists
     */
    public void addNewItem(String name, double price, int quantity) throws DuplicateItemException {
        FoodItemDto existing = foodItemDao.getFoodItemByName(name);
        if (existing != null) {
            throw new DuplicateItemException(name);
        }
        FoodItemDto item = new FoodItemDto(name, price);
        foodItemDao.addFoodItem(item, quantity);
    }

    /**
     * Increases the stock of an existing food item.
     *
     * @param name Item name
     * @param quantity Quantity to add
     */
    public void restockItem(String name, int quantity) {
        FoodItemDto item = foodItemDao.getFoodItemByName(name);
        if (item != null) {
            int current = foodItemDao.getInventory().get(item);
            foodItemDao.getInventory().put(item, current + quantity);
        }
    }

    /**
     * Removes a food item from the menu.
     *
     * @param name Item name
     */
    public void removeItem(String name) {
        foodItemDao.removeFoodItem(name);
    }

    /**
     * Retrieves the current menu with quantities.
     *
     * @return Map of FoodItemDto to quantity
     */
    public Map<FoodItemDto, Integer> getMenu() {
        return foodItemDao.getInventory();
    }

    /**
     * Finds a food item by name.
     *
     * @param name Item name
     * @return FoodItemDto if found, null otherwise
     */
    public FoodItemDto getFoodItemByName(String name) {
        return foodItemDao.getFoodItemByName(name);
    }
}
