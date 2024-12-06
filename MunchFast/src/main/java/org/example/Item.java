package org.example;

public abstract class Item {
    protected String name;
    protected double price;
    protected int quantity;
    protected String mainIngredients;
    protected boolean containsMeat;

    public abstract void consume();
}
