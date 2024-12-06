package org.example;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectivity {
    private static final String url = "jdbc:sqlite:munchdatatest.db";

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

    public static void createMenuTable() {
        String sql = "CREATE TABLE IF NOT EXISTS MENU (\n"
                + " ITEM_ID INTEGER PRIMARY KEY,\n"
                + " NAME TEXT NOT NULL, \n"
                + " PRICE DECIMAL, \n"
                + " QUANTITY INTEGER, \n"
                + " MENU_TYPE_ID INTEGER, \n"
                + " FOREIGN KEY (MENU_TYPE_ID) REFERENCES MENU_SCHEDULES (MENU_TYPE_ID)"
                + " );";
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Menu Schedule Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Create a Menu Schedule Table.
     * This class contains the different menu types (breakfast, lunch, dinner)
     *  Each menu types have different time slots.
     */
    public static void createMenuSchedulesTable() {
        String sql = "CREATE TABLE IF NOT EXISTS MENU_SCHEDULES (\n"
                + " MENU_TYPE_ID INTEGER PRIMARY KEY,\n"
                + " MENU_TYPE TEXT NOT NULL, \n"
                + " TIME_SLOT TIME"
                + " );";
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Menu Schedule Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

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
                + " DELIVERY_ADDRESS TEXT NOT NULL \n"
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
                + " STATUS TEXT NOT NULL, \n"
                + " DELIVERY_TIME INTEGER NOT NULL, \n"
                + " CUSTOMER_INSTRUCTIONS TEXT"
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
    // Stores the items in each order.
    public static void createOrderItemsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS ORDER_ITEMS (\n"
                + " ORDER_ITEM_ID INTEGER PRIMARY KEY, \n"
                + " ORDER_ID INTEGER, \n"
                + " ITEM_ID INTEGER, \n"
                + " QUANTITY INTEGER, \n"
                + " FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ORDER_ID),\n"
                + " FOREIGN KEY (ITEM_ID) REFERENCES MENU(ITEM_ID)"
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
     *
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


//(ITEM_ID, NAME, PRICE, QUANTITY, MENU_TYPE_ID)

    /**
     * Menu types ID:
     * 1: Breakfast
     * 2: Lunch
     * 3: Dinner
     */
    public static void addMenuItems(){
        String sql = "INSERT INTO MENU (ITEM_ID, NAME, PRICE, QUANTITY, MENU_TYPE_ID) VALUES " +
                "(1, 'Coffee', 3.50, 100, 1), " +
                "(2, 'Orange Juice', 5.99, 100, 1), " +
                "(3, 'Burger', 5.99, 100, 2), " +
                "(4, 'Ribs', 23.99, 100, 3), " +
                "(5, 'Rice', 6.50, 100, 3)," +
                "(6, 'Fries', 3.50, 100, 2);";
        try (Connection connection = connect();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Menu items inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting hardcoded menu items data: " + e.getMessage());
        }
    }

    public static void addMenuSchedule(){
        String sql = "INSERT INTO MENU_SCHEDULES (MENU_TYPE_ID, MENU_TYPE,TIME_SLOT) VALUES " +
                "(1, 'Breakfast', '08:00:00'), " +
                "(2, 'Lunch', '12:00:00'), " +
                "(3, 'Dinner', '18:00:00'); ";
        try (Connection connection = connect();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Menu schedules inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting hardcoded menu schedules data: " + e.getMessage());
        }
    }

    /**
     * View Menu (view the concrete item) of a Restaurant (by Type)
     */
    public static void viewMenu() {

    }

    /**
     * Status updated
     * Update this and do popup
     */
    public static void updateStatusByOrder(){

    }

    public static void main(String[] args) {

        createMenuTable();
        createMenuSchedulesTable();
        createOrdersTable();
        createOrderItemsTable();
        createCustomersTable();
        //  addCustomer(5,"Flor","Ko","mail@.com","432 234 4322","Westisland");
        // insert once
//        addMenuItems();
//        addMenuSchedule();

    }

}
