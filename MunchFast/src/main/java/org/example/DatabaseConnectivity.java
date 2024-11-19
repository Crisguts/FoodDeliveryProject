package org.example;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectivity {


    /**
     * Connect to the database
     *
     * @return Connection
     */
    private static Connection connect() {
        String url = "jdbc:sqlite:munchdata.db";
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
     * Create a Restaurant Table
     */
    public static void createRestaurantsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS RESTAURANTS (\n"
                + " RESTAURANT_ID INTEGER PRIMARY KEY, \n"
                + " NAME TEXT NOT NULL, \n"
                + " LOCATION TEXT NOT NULL, \n"
                + " RATING INT"
                + " );";
        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            statement.execute(sql);
            System.out.println("Restaurant Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create a Menu Schedule Table. This class contains the different menu types (breakfast, lunch, dinner)
     * of each restaurant. Each menu types have different time slots.
     */
    public static void createMenuSchedulesTable() {
        String sql = "CREATE TABLE IF NOT EXISTS MENU_SCHEDULES (\n"
                + " SCHEDULE_ID INTEGER PRIMARY KEY,\n"
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
     * Create a Restaurant Menu Schedule. This joins the restaurant to a menu schedule.
     * Assuming that each restaurant have the same time slots for each menu types,
     * creating this class will reduce data redundancy and allow to assign the same menu schedule
     * to every restaurant
     */
    public static void createRestaurantMenuSchedulesTable() {
        String sql = "CREATE TABLE IF NOT EXISTS RESTAURANT_MENU_SCHEDULES (\n"
                + " RESTAURANT_ID INTEGER,\n"
                + " SCHEDULE_ID INTEGER,\n"
                + " FOREIGN KEY (RESTAURANT_ID) REFERENCES RESTAURANTS(RESTAURANT_ID), \n"
                + " FOREIGN KEY (SCHEDULE_ID) REFERENCES MENU_SCHEDULES(SCHEDULE_ID), \n"
                + " PRIMARY KEY (RESTAURANT_ID, SCHEDULE_ID)"
                + " );";
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Restaurant Menu Schedule Table created successfully");
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
                + " DELIVERY_ADDRESS TEXT NOT NULL, \n"
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

    // ADDITIONAL FEATURE: REMOVE DRIVER IF THEY HAVE LESS THAN A 1-STAR RATING??

    /**
     * Create a Drivers table.
     */
    public static void createDriversTable() {
        String sql = "CREATE TABLE IF NOT EXISTS DRIVERS (\n"
                + " DRIVER_ID INTEGER PRIMARY KEY, \n"
                + " F_NAME TEXT NOT NULL, \n"
                + " L_NAME TEXT NOT NULL, \n"
                + " EMAIL TEXT NOT NULL, \n"
                + " PHONE_NUMBER TEXT NOT NULL, \n"
                + " LICENSE_PLATE TEXT NOT NULL, \n"
                + " LICENSE_NUMBER TEXT NOT NULL, \n"
                + " RATING INT, \n"
                + " AVAILABILITY TEXT NOT NULL, \n"
                + " ORDER_ID INTEGER DEFAULT NULL, \n"
                + " FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ORDER_ID)"
                + " );";
        // maybe add a check constraint for the allergy
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Driver Table created successfully");
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
                + " DRIVER_ID INTEGER, \n"
                + " RESTAURANT_ID INTEGER, \n"
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

    /**
     * Create a Drinks Table
     */
    public static void createDrinksTable() {
        String sql = "CREATE TABLE IF NOT EXISTS DRINKS (\n"
                + " DRINK_ID INTEGER PRIMARY KEY, \n"
                + " NAME TEXT NOT NULL,\n"
                + " COST DECIMAL(5,2) \n"
                + " QUANTITY INTEGER \n"
                + " MAIN_INGREDIENTS TEXT \n"
                + " CONTAINS_MEAT TEXT \n"
                + " );";
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Drinks Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create a Food Table (FOOD = MAIN COURSE)
     */
    public static void createFoodTable() {
        String sql = "CREATE TABLE IF NOT EXISTS FOOD (\n"
                + " FOOD_ID INTEGER PRIMARY KEY, \n"
                + " NAME TEXT NOT NULL,\n"
                + " COST DECIMAL(5,2) \n"
                + " QUANTITY INTEGER \n"
                + " MAIN_INGREDIENTS TEXT \n"
                + " CONTAINS_MEAT TEXT \n"
                + " );";
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Drinks Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create a SideDish Table
     */
    public static void createSideDishesTable() {
        String sql = "CREATE TABLE IF NOT EXISTS SIDE_DISHES (\n"
                + " SIDE_DISH_ID INTEGER PRIMARY KEY, \n"
                + " NAME TEXT NOT NULL,\n"
                + " COST DECIMAL(5,2) \n"
                + " QUANTITY INTEGER \n"
                + " MAIN_INGREDIENTS TEXT \n"
                + " CONTAINS_MEAT TEXT \n"
                + " );";
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Drinks Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *                   " DRIVER_ID INTEGER PRIMARY KEY, \n"
     *                 + " F_NAME TEXT NOT NULL, \n"
     *                 + " L_NAME TEXT NOT NULL, \n"
     *                 + " EMAIL TEXT NOT NULL, \n"
     *                 + " PHONE_NUMBER TEXT NOT NULL, \n"
     *                 + " LICENSE_PLATE TEXT NOT NULL, \n"
     *                 + " LICENSE_NUMBER TEXT NOT NULL, \n"
     *                 + " RATING INT, \n"
     *                 + " AVAILABILITY TEXT NOT NULL, \n"
     *                 + " ORDER_ID INTEGER DEFAULT NULL, \n"
     *                 + " FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ORDER_ID)"
     */
    public static void addDriver(){
        String sql = " INSERT INTO DRIVERS()";
    }

    /**
     * View Menu (view the concrete item) of a Restaurant (by Type)
     */
    public static void viewMenu() {
<<<<<<< Updated upstream
        String sql = " ";
=======

>>>>>>> Stashed changes
    }


}
