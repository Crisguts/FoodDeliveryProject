package org.example;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private static List<Item> items;

    /**
     * The Menu is hard coded
     */
    public ItemDAO() {
        items = new ArrayList<>();
        items.add(new Item(1, "Burger", 8.99, 100, "Dinner"));
        items.add(new Item(2, "Rice", 3.99, 100, "Breakfast"));
        items.add(new Item(3, "Orange Juice", 1.99, 100, "Lunch"));
        items.add(new Item(4, "Coffee", 1.99, 100, "Breakfast"));
        items.add(new Item(5, "Ribs", 21.99, 100, "Dinner"));
        items.add(new Item(6, "Fries", 4.99, 100, "Lunch"));
    }

    /**
     * Add a user
     *
     * @param item
     */
    public void addItem(Item item) {
        items.add(item);
    }

    public Item getItemById(int id) {
        for (Item item : items) {
            if (item.getItemId() == id) {
                return item;
            }
        }
        System.out.println("Item is not found");
        return null;
    }

    public boolean deleteItemById(int id) {
        return items.removeIf(item -> item.getItemId() == id);
    }

    public List<Item> getAllItems() {
        return items;
    }

    public void updateItemStock(int id, int quantity) {
        for (Item item : items) {
            if (item.getItemId() == id) {
                item.setStock(item.getStock() - quantity);
            }
        }
    }

    public String getAllItemsAsString() {
        StringBuilder result = new StringBuilder();

        for (Item item : items) {
            result.append(item.toString()).append("\n");
        }

        return result.toString();
    }

}
