package org.example;

import java.util.List;

public class Order {
    private List<Item> items;
    private Customer customer;
    private Driver driver;
    private Restaurant restaurant;
    private String status;
    private int orderID;
    private String customerInstructions;
    private int deliveryTime;

    // Getters and Setters...

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}