package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;               // Unique ID for the order
    private int customerId;            // ID of the customer placing the order
    private String status;             // Status of the order (e.g., "On the way", "Delivered")
    private List<Item> items;

    // Constructor, the order id will be incremented by 1
    public Order(int customerId, String status) {
        this.orderId = OrderDAO.LoadLastId() + 1;
        this.customerId = customerId;
        this.status = status;
        this.items = new ArrayList<>();  
    }

    // Items inside the Orders class
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item, int quantity) {
        item.setStock(quantity);
        this.items.add(item);
    }

    // Getters and Setters for Orders
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String convertIntMinutesToTime(int minutes) {
        int hours = minutes / 60;
        int remainMinutes = minutes % 60;
        return String.format("%02d:%02d", hours, remainMinutes);
    }

}
