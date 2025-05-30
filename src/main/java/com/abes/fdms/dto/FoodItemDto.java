package com.abes.fdms.dto;

import java.util.Objects;

public class FoodItemDto {
    private String name;
    private double price;

    public FoodItemDto(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) 
        	return true;
        if (o == null || getClass() != o.getClass()) 
        	return false;
        FoodItemDto foodItem = (FoodItemDto) o;
        return name.equalsIgnoreCase(foodItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override
    public String toString() {
        return name + "- Rs. " + price;
    }
}
