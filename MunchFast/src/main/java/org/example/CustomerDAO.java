package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private Connection connection;

    public CustomerDAO() {
        connection = DatabaseConnectivity.connect();
    }

    // this method is called when controller added a customer
    public static void addCustomer(int customer_id, String f_name, String l_name, String email, String phone_number
            , String delivery_address) {
        String sql = "INSERT INTO CUSTOMERS (CUSTOMER_ID, F_NAME, L_NAME, EMAIL, PHONE_NUMBER, DELIVER_ADDRESS) VALUES " +
                "(?, ?, ?, ?, ?, ? )";

        try (Connection connection = DatabaseConnectivity.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customer_id);
            preparedStatement.setString(2, f_name);
            preparedStatement.setString(3, l_name);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phone_number);
            preparedStatement.setString(6, delivery_address);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Caught SQLException inside the addCustomer()" + e.getMessage());
        }
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
