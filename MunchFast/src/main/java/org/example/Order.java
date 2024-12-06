package org.example;

import java.util.List;

public class Order {
    private List<Item> items;
    private Customer customer;
    private String status;
    // Getters and Setters...

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}