package com.abes.fdms.service;

import com.abes.fdms.dao.*;
import com.abes.fdms.dto.*;
import com.abes.fdms.exception.InvalidOrderException;
import com.abes.fdms.exception.InvalidQuantityException;
import com.abes.fdms.exception.ItemNotFoundException;
import com.abes.fdms.exception.DeliveryPersonUnavailableException;
import java.util.*;

/**
 * Service class for placing and managing orders.
 */
public class OrderService {
    private final DeliveryService deliveryService = new DeliveryService();
    private final OrderDao orderDao = new OrderDaoImpl();
    private final FoodItemDao foodItemDao = new FoodItemDaoImpl();

    /**
     * Places a new order for a customer.
     *
     * @param customer The customer placing the order
     * @param requestedItems Map of item names to quantities
     * @return The created OrderDto
     * @throws InvalidOrderException If the order is invalid
     * @throws InvalidQuantityException If any quantity is invalid
     * @throws ItemNotFoundException If any item is not found
     * @throws DeliveryPersonUnavailableException If no delivery person is available
     */
    public OrderDto placeOrder(
        CustomerDto customer,
        Map<String, Integer> requestedItems
    ) throws InvalidOrderException, InvalidQuantityException, ItemNotFoundException, DeliveryPersonUnavailableException {
        Map<FoodItemDto, Integer> orderItems = new HashMap<>();

        for (Map.Entry<String, Integer> entry : requestedItems.entrySet()) {
            String name = entry.getKey();
            int quantity = entry.getValue();

            if (quantity <= 0) {
                throw new InvalidQuantityException();
            }

            FoodItemDto item = foodItemDao.getFoodItemByName(name);

            if (item == null) {
                throw new ItemNotFoundException();
            }
            if (foodItemDao.getInventory().get(item) < quantity) {
                throw new InvalidOrderException();
            }

            orderItems.put(item, quantity);
        }

        DeliveryPersonDto dp = deliveryService.getAvailableDeliveryPerson();
        if (dp == null) {
            throw new DeliveryPersonUnavailableException();
        }

        for (Map.Entry<FoodItemDto, Integer> entry : orderItems.entrySet()) {
            foodItemDao.getInventory().put(entry.getKey(), foodItemDao.getInventory().get(entry.getKey()) - entry.getValue());
        }

        OrderDto order = new OrderDto(customer, dp, orderItems);
        orderDao.addOrder(order);
        return order;
    }

    /**
     * Retrieves all orders placed by a specific customer.
     *
     * @param customerId Customer ID
     * @return List of OrderDto for the customer
     */
    public List<OrderDto> getOrdersByCustomer(String customerId) {
        List<OrderDto> userOrders = new ArrayList<>();
        for (OrderDto o : orderDao.getOrders()) {
            if (o != null && o.getCustomer().getId().equals(customerId)) {
                userOrders.add(o);
            }
        }
        return userOrders;
    }

    /**
     * Retrieves all orders in the system.
     *
     * @return List of all OrderDto
     */
    public List<OrderDto> getAllOrders() {
        return orderDao.getOrders();
    }
}
