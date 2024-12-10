package org.example;

import java.util.List;

public class ItemController {
    private ItemDAO menuItemDAO;

    public ItemController() {
        menuItemDAO = new ItemDAO();
    }

    /**
     * Add an item
     *
     * @param id
     * @param name
     * @param price
     * @param quantity
     * @param menuSchedule
     */
    public void addItem(int id, String name, double price, int quantity, String menuSchedule) {
        Item item = new Item(id, name, price, quantity, menuSchedule);
        menuItemDAO.addItem(item);
    }

    /**
     * Get an Item by its ID
     *
     * @param id
     * @return
     */
    public Item getItemById(int id) {
        return menuItemDAO.getItemById(id);
    }

    /**
     * Return all Items
     *
     * @return
     */
    public List<Item> getAllItems() {
        return menuItemDAO.getAllItems();
    }

    public String getAllItemsAsString() {
        return menuItemDAO.getAllItemsAsString();
    }

    /**
     * Delete an Item
     *
     * @param id
     * @return
     */
    public boolean deleteItem(int id) {
        return menuItemDAO.deleteItemById(id);
    }

    /**
     * Update the stock of an Item
     *
     * @param id
     * @param quantity
     */
    public void updateItemStock(int id, int quantity) {
        menuItemDAO.updateItemStock(id, quantity);
    }


}
