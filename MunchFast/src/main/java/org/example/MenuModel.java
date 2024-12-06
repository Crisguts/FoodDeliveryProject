package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuModel {
    // Get Menu Items (from the database)
    public List<MenuItemDTO> getMenuItems() {
        List<MenuItemDTO> menuItems = new ArrayList<>();
        String sql = "SELECT * FROM MENU"; // SQL to retrieve all menu items

        try (Connection conn = DatabaseConnectivity.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int itemId = rs.getInt("ITEM_ID");
                String name = rs.getString("NAME");
                double price = rs.getDouble("PRICE");
                int quantity = rs.getInt("QUANTITY");
                int menuTypeId = rs.getInt("MENU_TYPE_ID");
                MenuItemDTO item = new MenuItemDTO(itemId, name, price, quantity, menuTypeId);
                menuItems.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving menu items: " + e.getMessage());
        }

        return menuItems;
    }

    // Insert Menu Item into the Database
    public void addMenuItem(MenuItemDTO item) {
        String sql = "INSERT INTO MENU (ITEM_ID, NAME, PRICE, QUANTITY, MENU_TYPE_ID) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnectivity.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, item.getItemId());
            pstmt.setString(2, item.getName());
            pstmt.setDouble(3, item.getPrice());
            pstmt.setInt(4, item.getStock());
            pstmt.setInt(5, item.getMenuTypeId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding menu item: " + e.getMessage());
        }
    }
}
