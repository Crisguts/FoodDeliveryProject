package org.example;

import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuItemDTO implements Item{
    private int itemId;
    @Setter
    private String name;
    @Setter
    private double price;
    @Setter
    private int stock;
    @Setter
    private String type;

    // Constructor
    public MenuItemDTO(int itemId, String name, double price, int stock, String type) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getType() {
        return type;
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
