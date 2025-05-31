package com.abes.fdms.exception;

public class DeliveryPersonAlreadyExistsException extends Exception {
    public DeliveryPersonAlreadyExistsException(String id) {
        super("Delivery person with ID '" + id + "' already exists.");
    }
}
