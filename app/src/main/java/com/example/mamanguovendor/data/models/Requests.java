package com.example.mamanguovendor.data.models;

public class Requests {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String description;
    private String quantitiy;
    private String location;
    private String status;
    private String message;
    private int totalCost;

    public Requests(String firstName, String lastName, String phoneNumber, String description, String quantitiy, String location, String message, int totalCost) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.quantitiy = quantitiy;
        this.location = location;
        this.message = message;
        this.totalCost = totalCost;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getQuantitiy() {
        return quantitiy;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public String getLocation() {
        return location;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
