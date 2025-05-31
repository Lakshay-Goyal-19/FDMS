package com.abes.fdms.util;

import com.abes.fdms.dto.*;
import java.util.*;

public class CollectionUtil {
    public static final Map<String, CustomerDto> customers = new HashMap<>();
    public static final Map<String, ManagerDto> managers = new HashMap<>();
    public static final Map<String, DeliveryPersonDto> deliveryPersons = new HashMap<>();
    public static final Map<FoodItemDto, Integer> inventory = new HashMap<>();
    public static final List<OrderDto> orders = new ArrayList<>();

    static {
        // Dummy managers
        managers.put("Lakshay", new ManagerDto("Lakshay", "Default Manager", "lakshay@gmail.com", "1111111111", "Lakshay@1"));

        // Dummy customers
        customers.put("Customer1", new CustomerDto("tisha123", "Tisha", "tisha@email.com", "8888888888", "p1"));
        customers.put("Customer2", new CustomerDto("anusha123", "Anusha", "anusha@email.com", "7777777777", "p2"));

        // Dummy delivery persons (with email and phone)
        deliveryPersons.put("Manu", new DeliveryPersonDto("Manu", "Manu", "manu@gmail.com", "9000000001"));
        deliveryPersons.put("Tanmay", new DeliveryPersonDto("Tanmay", "Tanmay", "tanmay@gmail.com", "9000000002"));

        // Dummy food items
        FoodItemDto pizza = new FoodItemDto("Pizza", 100.0);
        FoodItemDto burger = new FoodItemDto("Burger", 50.0);
        FoodItemDto fries = new FoodItemDto("Fries", 50.0);
        inventory.put(pizza, 20);
        inventory.put(burger, 30);
        inventory.put(fries, 50);
    }

    // Add this method to reset all collections
    public static void reset() {
        customers.clear();
        managers.clear();
        deliveryPersons.clear();
        inventory.clear();
        orders.clear();
    }
}
