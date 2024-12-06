package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    public List<OrderDTO> getOrdersByCustomerId(int customerId) {
        String sql = "SELECT * FROM ORDERS WHERE CUSTOMER_ID = ?";
        List<OrderDTO> orders = new ArrayList<>();

        try (Connection connection = DatabaseConnectivity.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                orders.add(new OrderDTO(
                        rs.getInt("ORDER_ID"),
                        rs.getInt("CUSTOMER_ID"),
                        rs.getString("STATUS"),
                        rs.getInt("DELIVERY_TIME"),
                        rs.getString("CUSTOMER_INSTRUCTIONS")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching orders: " + e.getMessage());
        }

        return orders;
    }

    public void createOrder(OrderDTO order) {
        String sql = "INSERT INTO ORDERS (CUSTOMER_ID, STATUS, DELIVERY_TIME, CUSTOMER_INSTRUCTIONS) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectivity.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, order.getOrderId());
            pstmt.setInt(1, order.getCustomerId());
            pstmt.setString(2, order.getStatus());
            pstmt.setInt(3, order.getDeliveryTime());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error creating order: " + e.getMessage());
        }
    }
}