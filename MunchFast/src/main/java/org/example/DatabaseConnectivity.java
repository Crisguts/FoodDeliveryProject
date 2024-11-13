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
    public static void createRestaurantTable() {
        String sql = "CREATE TABLE IF NOT EXISTS RESTAURANTS (\n"
                + " RESTAURANT_ID INTEGER PRIMARY KEY, \n"
                + " NAME TEXT NOT NULL, \n"
                + " LOCATION TEXT NOT NULL, \n"
                + " RATING NUMERIC"
                + " );";
    }

    /**
     * Create a Menu Schedule Table
     */
//    public static void menuSchedule(){
//    String sql = "CREATE TABLE IF NOT EXISTS MENU_SCHEDULES (\n"
//            + " SCHEDULE_ID INTEGER PRIMARY KEY,\n"
//            + " MENU_TYPE TEXT NOT NULL, \n"
//            + " TIME_SLOT TIME"
//            + " );";
//    try(Connection connection = connect();
//        Statement statement = connection.createStatement()){
//
//    }
//
//    }

    public static void restaurantMenuSchedule(){
    String sql = "CREATE TABLE IF NOT EXISTS RESTAURANT_MENU_SCHEDULES (\n"
            + " RESTAURANT_ID INTEGER,\n"
            + " SCHEDULE_ID INTEGER,\n"
            + " FOREIGN KEY (RESTAURANT_ID) REFERENCES RESTAURANTS(RESTAURANT_ID), \n"
            + " TIME_SLOT TIME"
            + " );";
    }



    public static void createCustomerTable() {

    }

    public static void createDriverTable() {

    }
}
