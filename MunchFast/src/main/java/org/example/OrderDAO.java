package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection connection;

    public OrderDAO() {
        connection = DatabaseConnectivity.connect();
    }

    public static void addOrder(){

    }

    public static Order getOrdersById(int id) {
        String sql = "SELECT * FROM ORDERS WHERE ORDER_ID = ?";
        Order order = null;

        try (
                Connection connection = DatabaseConnectivity.connect();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                order = new Order(
                        rs.getInt("ORDER_ID"),
                        rs.getInt("CUSTOMER_ID"),
                        rs.getString("STATUS"),
                        rs.getInt("DELIVER_TIME")
                );
                order.setCustomerId(rs.getInt("ORDER_ID")); // Set the database-generated ID
            }
        } catch (
                SQLException e) {
            System.err.println("Error fetching customer by email: " + e.getMessage());
        }
        return order;
    }

    // Method to get all customers from the database
    public static List<Order> getAllOrders() {
        String sql = "SELECT * FROM ORDERS";
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DatabaseConnectivity.connect();
             Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("ORDER_ID"),
                        rs.getInt("CUSTOMER_ID"),
                        rs.getString("STATUS"),
                        rs.getInt("DELIVERY_TIME")
                );
                order.setCustomerId(rs.getInt("CUSTOMER_ID")); // Set the database-generated ID
                orders.add(order);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all customers: " + e.getMessage());
        }
        return orders;
    }
}
