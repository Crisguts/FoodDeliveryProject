package org.example;

public class Item {
        private int itemId;
        private String name;
        private double price;
        private int stock;
        private String menuSchedule; // Breakfast, for example

        public Item(int itemId, String name, double price, int stock, String menuSchedule) {
                this.itemId = itemId;
                this.name = name;
                this.price = price;
                this.stock = stock;
                this.menuSchedule = menuSchedule;
        }

        public int getItemId() {
                return itemId;
        }

        public void setItemId(int itemId) {
                this.itemId = itemId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public double getPrice() {
                return price;
        }

        public void setPrice(double price) {
                this.price = price;
        }

        public int getStock() {
                return stock;
        }

        public void setStock(int stock) {
                this.stock = stock;
        }

        public String getMenuSchedule() {
                return menuSchedule;
        }

        public void setMenuSchedule(String menuSchedule) {
                this.menuSchedule = menuSchedule;
        }
}



