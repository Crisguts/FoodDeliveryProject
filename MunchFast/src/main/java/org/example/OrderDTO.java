package org.example;

public class OrderDTO {
        private int orderId;               // Unique ID for the order
        private int customerId;            // ID of the customer placing the order
        private String status;             // Status of the order (e.g., "On the way", "Delivered")
        private int deliveryTime;          // Delivery time in minutes

        // Constructor
        public OrderDTO(int orderId, int customerId, String status, int deliveryTime, String customerInstructions) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.status = status;
            this.deliveryTime = deliveryTime;
        }

        // Getters and Setters
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

