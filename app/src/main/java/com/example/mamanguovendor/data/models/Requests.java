package com.example.mamanguovendor.data.models;

public class Requests {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String description;
    private int quantitiy;
    private int cost;

    public Requests(String firstName, String lastName, String phoneNumber, String description, int quantitiy, int cost) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.quantitiy = quantitiy;
        this.cost = cost;
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

    public int getQuantitiy() {
        return quantitiy;
    }

    public int getCost() {
        return cost;
    }
}
