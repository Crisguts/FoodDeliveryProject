package org.example;

public interface Item {
        String getName();
        double getPrice();
        int getStock();
        void setStock(int stock);
        String getType(); // Breakfast, for example
}

