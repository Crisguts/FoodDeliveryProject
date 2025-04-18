package org.example;

public class Restaurant {
    private final String name = "Munch Fast";

    private Restaurant() {
        this.getInstance();
    }

    public static void getInstance() {
        new Login().setVisible(true);
        DatabaseConnectivity.createCustomersTable();
        DatabaseConnectivity.createOrdersTable();
        DatabaseConnectivity.createOrderItemsTable();
    }
}
