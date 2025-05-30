package com.abes.fdms.dto;

import java.util.Map;

public class OrderDto {
    private CustomerDto customer;
    private DeliveryPersonDto deliveryPerson;
    private Map<FoodItemDto, Integer> itemsOrdered;
    private String status;

    public OrderDto(CustomerDto customer, DeliveryPersonDto deliveryPerson, Map<FoodItemDto, Integer> itemsOrdered) {
        this.customer = customer;
        this.deliveryPerson = deliveryPerson;
        this.itemsOrdered = itemsOrdered;
        this.status = "Placed";
        deliveryPerson.setAvailable(false);
    }

    public void completeOrder() {
        this.status = "Completed";
        deliveryPerson.setAvailable(true);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public Map<FoodItemDto, Integer> getItemsOrdered() {
        return itemsOrdered;
    }
    
    public DeliveryPersonDto getDeliveryPerson() {
        return deliveryPerson;
    }
}
