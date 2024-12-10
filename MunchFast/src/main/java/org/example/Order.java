package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {
        private int orderId;               // Unique ID for the order
        private int customerId;            // ID of the customer placing the order
        private String status;             // Status of the order (e.g., "On the way", "Delivered")
        private int deliveryTime;          // Delivery time in minutes
        private List<Item> items;

        // Constructor, the order id will be incremented by 1
        public Order(int customerId, String status, int deliveryTime) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.status = status;
            this.deliveryTime = deliveryTime;
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

        public int getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(int deliveryTime) {
            this.deliveryTime = deliveryTime;
        }


    }

