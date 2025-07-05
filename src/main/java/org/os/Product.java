package org.os;

import java.time.LocalDate;

public class Product {
    private String name;
    private double price;
    private int quantity;

    private boolean shippable;
    private double weight;


    private LocalDate expiryDate;

    public Product(String name, double price, int quantity,
                   boolean shippable, double weight,
                    LocalDate expiryDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shippable = shippable;
        this.weight = weight;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }

    public boolean isShippable() {
        return shippable;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isExpired() {
        return expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }
}
