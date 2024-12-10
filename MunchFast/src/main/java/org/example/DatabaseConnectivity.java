package org.example;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectivity {
    public static final String url = "jdbc:sqlite:munchfastdatatest6.db";

    /**
     * Connect to the database
     *
     * @return Connection
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Create a Customers table.
     */
    public static void createCustomersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS CUSTOMERS (\n"
                + " CUSTOMER_ID INTEGER PRIMARY KEY, \n"
                + " F_NAME TEXT NOT NULL, \n"
                + " L_NAME TEXT NOT NULL, \n"
                + " EMAIL TEXT NOT NULL, \n"
                + " PHONE_NUMBER TEXT NOT NULL, \n"
                + " DELIVER_ADDRESS TEXT \n"
                + " );";
        // maybe add a check constraint for the allergy
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Customers Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create Orders Table
     */
    public static void createOrdersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS ORDERS (\n"
                + " ORDER_ID INTEGER PRIMARY KEY, \n"
                + " CUSTOMER_ID INTEGER, \n"
                + " STATUS TEXT NOT NULL DEFAULT 'ONGOING', \n"
                + " FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS(CUSTOMER_ID) \n"
                + " CHECK (STATUS = 'ONGOING' OR STATUS = 'PENDING' OR STATUS = 'DELIVERED')"
                + " );";

        // DELIVERY_TIME ARE MEASURED IN MINUTES, HENCE WHY IT'S AN INTEGER
        // STATUS: ON THE WAY, OR DELIVERED
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Order Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Stores the items in each order.
     * Item_ID is not a foreign key since
     */
    public static void createOrderItemsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS ORDER_ITEMS (\n"
                + " ORDER_ID INTEGER, \n"
                + " ITEM_ID INTEGER, \n"
                + " QUANTITY INTEGER, \n"
                + " FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ORDER_ID)\n"
                + " );";

        // DELIVERY_TIME ARE MEASURED IN MINUTES, HENCE WHY IT'S AN INTEGER
        // STATUS: ON THE WAY, OR DELIVERED
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Order Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * @param customer_id
     * @param f_name
     * @param l_name
     * @param email
     * @param phone_number
     * @param delivery_address
     */
    public static void addCustomer(int customer_id, String f_name, String l_name, String email, String phone_number
            , String delivery_address) {
        String sql = "INSERT INTO CUSTOMERS (CUSTOMER_ID, F_NAME, L_NAME, EMAIL, PHONE_NUMBER, DELIVER_ADDRESS) VALUES " +
                "(?, ?, ?, ?, ?, ? )";

        try (Connection connection = connect();
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

    public static void main(String[] args) {
        createOrdersTable();
        createOrderItemsTable();
        createCustomersTable();
    }

}
