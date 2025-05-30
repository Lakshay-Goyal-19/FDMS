package com.abes.fdms.dto;

public abstract class UserDto {
    protected String id;
    protected String name;
    protected String email;
    protected String phoneNumber;

    public UserDto(String id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public abstract void showProfile();

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
