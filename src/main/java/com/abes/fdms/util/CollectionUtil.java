package com.abes.fdms.util;

import com.abes.fdms.dto.*;
import java.util.*;

/**
 * Utility class that holds in-memory collections for customers, managers, delivery persons,
 * food inventory, and orders. Used as a simple data store for the application.
 */
public class CollectionUtil {
    /** Map of customer ID to CustomerDto */
    public static final Map<String, CustomerDto> customers = new HashMap<>();
    /** Map of manager ID to ManagerDto */
    public static final Map<String, ManagerDto> managers = new HashMap<>();
    /** Map of delivery person ID to DeliveryPersonDto */
    public static final Map<String, DeliveryPersonDto> deliveryPersons = new HashMap<>();
    /** Map of FoodItemDto to its available quantity */
    public static final Map<FoodItemDto, Integer> inventory = new HashMap<>();
    /** List of all orders */
    public static final List<OrderDto> orders = new ArrayList<>();

    static {
        // Dummy managers
        managers.put("m123", new ManagerDto("m123", "Default Manager", "manu@gmail.com", "1111111111", "Manu@1"));

        // Dummy customers
        customers.put("tisha123", new CustomerDto("tisha123", "Tisha", "tisha@email.com", "8888888888", "Tish@1"));
        customers.put("anusha123", new CustomerDto("anusha123", "Anusha", "anusha@email.com", "7777777777", "Anush@1"));

        // Dummy delivery persons
        deliveryPersons.put("Lakshay", new DeliveryPersonDto("Lakshay", "Lakshay", "lakshay@gmail.com", "9000000001"));
        deliveryPersons.put("Anshika", new DeliveryPersonDto("Anshika", "Anshika", "anshika@gmail.com", "9000000002"));

        // Dummy food items
        FoodItemDto pizza = new FoodItemDto("Pizza", 100.0);
        FoodItemDto burger = new FoodItemDto("Burger", 50.0);
        FoodItemDto fries = new FoodItemDto("Fries", 50.0);
        inventory.put(pizza, 20);
        inventory.put(burger, 30);
        inventory.put(fries, 50);
    }

    /**
     * Resets all collections to empty. Useful for testing or reinitialization.
     */
    public static void reset() {
        customers.clear();
        managers.clear();
        deliveryPersons.clear();
        inventory.clear();
        orders.clear();
    }
}
