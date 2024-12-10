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
     * GET THE HIGHEST ID IN THE TABLE 
     * @return 
     */
    public static int LoadLastId() {
        String sql = "SELECT MAX(CUSTOMER_ID) AS MAX_ID FROM CUSTOMERS;";
        try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("MAX_ID");
            }
        } catch (SQLException e) {
            System.err.println("Error loading last ID: " + e.getMessage());
        }
        return 0; // Default ID if the table is empty
    }

    /**
     * Get last customer from the Customer Table
     *
     * @return
     */
    public static Customer getLastCustomer() {
        String sql = "SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID = (SELECT MAX(CUSTOMER_ID) FROM CUSTOMERS);";
        Customer customer = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                customer = new Customer(
                        rs.getString("F_NAME"),
                        rs.getString("L_NAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONE_NUMBER"),
                        rs.getString("DELIVER_ADDRESS")
                );
                customer.setCustomerId(rs.getInt("CUSTOMER_ID")); // Set the ID
            }
        } catch (SQLException e) {
            System.err.println("Error fetching last customer: " + e.getMessage());
            return null;
        } catch (InvalidArgumentException iae) {
            System.err.println("Error fetching last customer: " + iae.getMessage());
            return null;
        }

        return customer;
    }

    /**
     * id = getId() ... this method is called when controller added a customer
     *
     * @param customer
     */
    public void addCustomer(Customer customer) {

        String getMaxIdSql = "SELECT MAX(CUSTOMER_ID) AS MAX_ID FROM CUSTOMERS";
        String insertSql = "INSERT INTO CUSTOMERS (CUSTOMER_ID, F_NAME, L_NAME, EMAIL, PHONE_NUMBER, DELIVER_ADDRESS) VALUES (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement getIdStmt = connection.prepareStatement(getMaxIdSql); ResultSet rs = getIdStmt.executeQuery()) {

            int nextId = 1; // Default ID for the first customer
            if (rs.next()) {
                nextId = rs.getInt("MAX_ID") + 1; // Increment the max ID
            }

            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                insertStmt.setInt(1, nextId); // Use the generated ID
                insertStmt.setString(2, customer.getFirstName());
                insertStmt.setString(3, customer.getLastName());
                insertStmt.setString(4, customer.getEmail());
                insertStmt.setString(5, customer.getPhone());
                insertStmt.setString(6, customer.getDeliveryAddress());
                insertStmt.executeUpdate();
            }

            customer.setCustomerId(nextId); // Update the customer object with the generated ID

        } catch (SQLException e) {
            System.err.println("Error adding customer: " + e.getMessage());
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
                            rs.getString("DELIVER_ADDRESS")
                    );
                    customer.setCustomerId(rs.getInt("CUSTOMER_ID"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching the customer by its ID: " + e.getMessage());
        } catch (InvalidArgumentException iae) {
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
