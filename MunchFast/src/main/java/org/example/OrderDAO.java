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
     * GET THE HIGHEST ID IN THE TABLE 
     * @return 
     */
     public static int LoadLastId() {
        String sql = "SELECT MAX(ORDER_ID) AS MAX_ID FROM ORDERS;";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("MAX_ID");
            }
        } catch (SQLException e) {
            System.err.println("Error loading last ID: " + e.getMessage());
        }
        return 0; // Default ID if the table is empty
    }

    /**
     * Linked Table Between Order and Item
     * Since Item is hard coded, retrieving the ITEM_ID using the ItemDAO
     * @param orderId
     * @param items
     */
    public void addOrderItems(int orderId, List<Item> items) {
        String sql = "INSERT INTO ORDER_ITEMS (ORDER_ID, ITEM_ID, QUANTITY) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (Item item : items) {
                pstmt.setInt(1, orderId);
                pstmt.setInt(2, item.getItemId()); //Use ITEM_ID from the hardcoded data
                pstmt.setInt(3, item.getStock());
                pstmt.executeUpdate();
            }
            System.out.println("Order items added successfully for order ID: " + orderId);
        } catch (SQLException e) {
            System.err.println("Error adding order items: " + e.getMessage());
        }
    }
    
    public Order getOrderWithItems(int orderId) {
    String sql = "SELECT ITEM_ID, QUANTITY FROM ORDER_ITEMS WHERE ORDER_ID = ?";
    Order order = null;

    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, orderId);
        try (ResultSet rs = pstmt.executeQuery()) {
            ItemDAO itemDAO = new ItemDAO(); // Use the hardcoded data
            order = new Order(orderId, "ONGOING"); // Initialize order with placeholders

            while (rs.next()) {
                int itemId = rs.getInt("ITEM_ID");
                int quantity = rs.getInt("QUANTITY");
                Item item = itemDAO.getItemById(itemId); // Fetch from hardcoded list
                if (item != null) {
                    order.addItem(item, quantity);
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error fetching order with items: " + e.getMessage());
    }

    return order;
}

    public static void updateStatusToPending(int id){
        String sql = "UPDATE ORDERS SET STATUS = 'PENDING' WHERE ORDER_ID = ?;";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating the status to PENDING: " + e.getMessage());
        }
    }

    public static void updateStatusToDelivered(int id){
        String sql = "UPDATE ORDERS SET STATUS = 'DELIVERED' WHERE ORDER_ID = ?;";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating the status to Delivered: " + e.getMessage());
        }
    }

    /**
     * Add a new Order
     * @param order
     */
    public void addOrder(Order order) {
        String sql = "INSERT INTO ORDERS (ORDER_ID ,CUSTOMER_ID, STATUS) VALUES " +
                "(?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getCustomerId());
            preparedStatement.setString(3, order.getStatus());
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
                            rs.getString("STATUS")
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
                            rs.getString("STATUS")
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
        //TO STRING
        StringBuilder result = new StringBuilder();


        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("CUSTOMER_ID"),
                        rs.getString("STATUS")
                );
                order.setOrderId(rs.getInt("ORDER_ID"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all customers: " + e.getMessage());
        }
        return orders;
    }

    public String getAllOrdersAsString() {
        String sql = "SELECT * FROM ORDERS";
        List<Order> orders = new ArrayList<>();
        //TO STRING
        StringBuilder result = new StringBuilder();


        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int orderId = rs.getInt("ORDER_ID");
                int customerId = rs.getInt("CUSTOMER_ID");
                String status = rs.getString("STATUS");

                // Format the row as a string
                result.append("Order ID: ").append(orderId)
                        .append(", Customer ID: ").append(customerId)
                        .append(", Status: ").append(status)
                        .append("\n");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all orders: " + e.getMessage());
        }

        return result.toString();
    }
}
