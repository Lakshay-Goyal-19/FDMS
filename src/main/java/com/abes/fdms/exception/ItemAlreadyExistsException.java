package com.abes.fdms.exception;

public class ItemAlreadyExistsException extends Exception {
    public ItemAlreadyExistsException(String name) {
        super("Item '" + name + "' already exists in the menu.");
    }
}
