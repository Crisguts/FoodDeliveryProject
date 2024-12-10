package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private static Connection connection;

    public CustomerDAO() {
        connection = DatabaseConnectivity.connect();
    }

    /**
     * id = getId() ...
     * this method is called when controller added a customer
     *
     * @param customer
     */
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO CUSTOMERS (CUSTOMER_ID, F_NAME, L_NAME, EMAIL, PHONE_NUMBER, DELIVER_ADDRESS) VALUES " +
                "(?, ?, ?, ?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6, customer.getDeliveryAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Caught SQLException inside the addCustomer()" + e.getMessage());
        }
    }

    /**
     * Delete Customer
     *
     * @param customerId
     */
    public boolean deleteCustomer(int customerId) {
        // check if the customer exists
        String sql = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, customerId);

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected > 0) {
                System.out.println("Customer with ID " + customerId + " was successfully deleted.");
                return true;
            } else {
                System.out.println("No customer found with ID " + customerId + ". Deletion not performed.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Caught SQLException inside the deleteCustomer()" + e.getMessage());
        }
        return false;
    }

    /**
     * Method to get a customer by email (e.g., for login)
     *
     * @param email
     * @return
     */
    public Customer getCustomerByEmail(String email) {
        if (email == null || email.isEmpty()) {
            System.err.println("Email cannot be null or empty");
            return null;
        }

        String sql = "SELECT * FROM CUSTOMERS WHERE EMAIL = ?";
        Customer customer = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(
                            rs.getString("F_NAME"),
                            rs.getString("L_NAME"),
                            rs.getString("EMAIL"),
                            rs.getString("PHONE_NUMBER"),
                            rs.getString("DELIVER_ADDRESS")
                    );
                    customer.setCustomerId(rs.getInt("CUSTOMER_ID")); // Set the database-generated ID
                } else {
                    System.out.println("No customer found with email: " + email);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching customer by email: " + e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    /**
     * Get the customer by their ID
     *
     * @param id
     * @return
     */
    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID = ?;";
        Customer customer = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(

                            rs.getString("F_NAME"),
                            rs.getString("L_NAME"),
                            rs.getString("EMAIL"),
                            rs.getString("PHONE_NUMBER"),
                            rs.getString("DELIVERY_ADDRESS")
                    );
                    customer.setCustomerId( rs.getInt("CUSTOMER_ID"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching the customer by its ID: " + e.getMessage());
        } catch (InvalidArgumentException iae){
            System.err.println("Invalid ");
        }
        return customer;
    }

    /**
     * Method to get all customers from the database
     *
     * @return
     */
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM CUSTOMERS";
        List<Customer> customers = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {

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
        } catch (InvalidArgumentException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

}
