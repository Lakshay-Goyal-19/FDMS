package com.abes.fdms.service;

import com.abes.fdms.dao.*;
import com.abes.fdms.dto.*;
import com.abes.fdms.exception.InvalidOrderException;
import java.util.*;

public class OrderService {
    private final DeliveryService deliveryService = new DeliveryService();
    private final OrderDao orderDao = new OrderDaoImpl();
    private final FoodItemDao foodItemDao = new FoodItemDaoImpl();

    public OrderDto placeOrder(CustomerDto customer, Map<String, Integer> requestedItems) throws InvalidOrderException {
        Map<FoodItemDto, Integer> orderItems = new HashMap<>();

        for (Map.Entry<String, Integer> entry : requestedItems.entrySet()) {
            String name = entry.getKey();
            int quantity = entry.getValue();

            if (quantity <= 0) {
                throw new InvalidOrderException("Quantity for item '" + name + "' must be greater than zero.");
            }

            FoodItemDto item = foodItemDao.getFoodItemByName(name);

            if (item == null) {
                throw new InvalidOrderException("Item does not exist: " + name);
            }
            if (foodItemDao.getInventory().get(item) < quantity) {
                throw new InvalidOrderException("Insufficient stock for item: " + name);
            }

            orderItems.put(item, quantity);
        }

        DeliveryPersonDto dp = deliveryService.getAvailableDeliveryPerson();
        if (dp == null) {
            throw new InvalidOrderException("No delivery person available");
        }

        for (Map.Entry<FoodItemDto, Integer> entry : orderItems.entrySet()) {
            foodItemDao.getInventory().put(entry.getKey(), foodItemDao.getInventory().get(entry.getKey()) - entry.getValue());
        }

        OrderDto order = new OrderDto(customer, dp, orderItems);
        orderDao.addOrder(order);
        return order;
    }

    public List<OrderDto> getOrdersByCustomer(String customerId) {
        List<OrderDto> userOrders = new ArrayList<>();
        for (OrderDto o : orderDao.getOrders()) {
            if (o != null && o.getCustomer().getId().equals(customerId)) {
                userOrders.add(o);
            }
        }
        return userOrders;
    }

    // New method for manager to view all orders
    public List<OrderDto> getAllOrders() {
        return orderDao.getOrders();
    }
}
