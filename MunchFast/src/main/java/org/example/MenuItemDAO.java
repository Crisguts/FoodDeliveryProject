package org.example;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuItemDAO {
    @Getter
    private int itemId;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double price;
    @Getter
    @Setter
    private int stock;
    @Getter
    @Setter
    private int menuTypeId;
    private String type;

    // Constructor
    public MenuItemDAO(int itemId, String name, double price, int stock, int menuTypeId) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.menuTypeId = menuTypeId;
    }

    public static void updateStock(int itemId, int quantityOrdered) {
        String sql = "UPDATE MENU SET QUANTITY = QUANTITY - ? WHERE ITEM_ID = ?";
        try (Connection connection = DatabaseConnectivity.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, quantityOrdered);
            pstmt.setInt(2, itemId);
            pstmt.executeUpdate();
            System.out.println("Stock updated for item ID " + itemId);
        } catch (SQLException e) {
            System.err.println("Error updating stock: " + e.getMessage());
        }
    }
}
