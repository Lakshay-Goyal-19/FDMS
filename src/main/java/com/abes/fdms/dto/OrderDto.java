package com.abes.fdms.dto;

import com.abes.fdms.util.OrderCostUtil;
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


    public String orderDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order for ").append(customer.getName()).append("\n");
        for (Map.Entry<FoodItemDto, Integer> entry : itemsOrdered.entrySet()) {
            sb.append(entry.getKey()).append(" x ").append(entry.getValue()).append("\n");
        }
        double totalCost = OrderCostUtil.calculateTotalCost(this);
        sb.append("Total Cost: Rs ").append(String.format("%.2f", totalCost)).append("\n");
        sb.append("Status: ").append(status);
        return sb.toString();
    }
}
