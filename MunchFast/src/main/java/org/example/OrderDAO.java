package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static Connection connection;

    public OrderDAO() {
        connection = DatabaseConnectivity.connect();
    }

    /**
     * Linked Table Between Order and Item
     * @param orderId
     * @param items
     */
    public void addOrderItems(int orderId, List<Item> items) {
        String sql = "INSERT INTO ORDER_ITEMS (ORDER_ID, ITEM_ID, QUANTITY) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (Item item : items) {
                pstmt.setInt(1, orderId);
                pstmt.setInt(2, item.getItemId());
                pstmt.setInt(3, item.getStock());
                pstmt.executeUpdate();
            }
            System.out.println("Order items added successfully for order ID: " + orderId);
        } catch (SQLException e) {
            System.err.println("Error adding order items: " + e.getMessage());
        }
    }


    /**
     * Add a new Order
     * @param order
     */
    public void addOrder(Order order) {
        String sql = "INSERT INTO ORDERS (ORDER_ID ,CUSTOMER_ID, STATUS, DELIVERY_TIME) VALUES " +
                "(?, ?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getCustomerId());
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setInt(4, order.getDeliveryTime());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Caught SQLException inside the addCustomer()" + e.getMessage());
        }
    }

    /**
     * Delete an Order
     * @param orderId
     * @return
     */
    public boolean deleteOrder(int orderId) {
        String sql = "DELETE FROM ORDERS WHERE ORDER_ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);

            int rowAffected = pstmt.executeUpdate();

            if (rowAffected > 0) {
                System.out.println("Order with ID " + orderId + " was successfully deleted.");
                return true;
            } else {
                System.out.println("No order found with ID " + orderId + ". Deletion not performed.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Caught SQLException inside the deleteOrder()" + e.getMessage());
        }
        return false;
    }

    /**
     * Retrieve Order by its ID (Returning 1 order)
     *
     * @param id
     * @return
     */
    public Order getOrdersById(int id) {
        String sql = "SELECT * FROM ORDERS WHERE ORDER_ID = ?";
        Order order = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    order = new Order(
                            rs.getInt("CUSTOMER_ID"),
                            rs.getString("STATUS"),
                            rs.getInt("DELIVER_TIME")
                    );
                    order.setOrderId(rs.getInt("ORDER_ID"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching customer by email: " + e.getMessage());
        }
        return order;
    }

    /**
     * Retrieve Order by its Customer (Returning multiple)
     *
     * @param id
     * @return
     */
    public List<Order> getOrdersByCustomer(int id) {
        String sql = "SELECT * FROM ORDERS WHERE CUSTOMER_ID = ?";
        List<Order> orders = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order(
                            rs.getInt("CUSTOMER_ID"),
                            rs.getString("STATUS"),
                            rs.getInt("DELIVER_TIME")
                    );
                    order.setOrderId(rs.getInt("ORDER_ID"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching customer by email: " + e.getMessage());
        }
        return orders;
    }

    /**
     * Retrieve all Orders
     *
     * @return
     */
    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM ORDERS";
        List<Order> orders = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("CUSTOMER_ID"),
                        rs.getString("STATUS"),
                        rs.getInt("DELIVER_TIME")
                );
                order.setOrderId(rs.getInt("ORDER_ID"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all customers: " + e.getMessage());
        }
        return orders;
    }
}
