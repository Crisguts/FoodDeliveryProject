package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private Connection connection;

    public CustomerDAO() {
        connection = DatabaseConnectivity.connect();
    }

    // Method to get a customer by email (e.g., for login)
    public static Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM CUSTOMERS WHERE EMAIL = ?";
        Customer customer = null;

        try (Connection connection = DatabaseConnectivity.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                customer = new Customer(
                        rs.getString("F_NAME"),
                        rs.getString("L_NAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONE_NUMBER"),
                        rs.getString("DELIVER_ADDRESS")
                );
                customer.setCustomerId(rs.getInt("CUSTOMER_ID")); // Set the database-generated ID
            }
        } catch (SQLException e) {
            System.err.println("Error fetching customer by email: " + e.getMessage());
        }
        return customer;
    }

    // Method to get all customers from the database
    public static List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM CUSTOMERS";
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DatabaseConnectivity.connect();
             Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getString("F_NAME"),
                        rs.getString("L_NAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONE_NUMBER"),
                        rs.getString("DELIVER_ADDRESS")
                );
                customer.setCustomerId(rs.getInt("CUSTOMER_ID")); // Set the database-generated ID
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all customers: " + e.getMessage());
        }
        return customers;
    }

}
