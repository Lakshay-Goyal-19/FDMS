package com.abes.fdms.exception;

public class NoDeliveryPersonAvailableException extends Exception {
    public NoDeliveryPersonAvailableException() {
        super("No delivery person is currently available. Please try again later.");
    }
}
