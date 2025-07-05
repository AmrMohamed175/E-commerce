package org.os;

import java.time.LocalDate;

public class Product {
    private String name;
    private double price;
    private int quantity;

    private boolean shippable;
    private double weight;

    private boolean expirable;
    private LocalDate expiryDate;

    public Product(String name, double price, int quantity,
                   boolean shippable, double weight,
                   boolean expirable, LocalDate expiryDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shippable = shippable;
        this.weight = weight;
        this.expirable = expirable;
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

    public boolean isExpirable() {
        return expirable;
    }

    public boolean isExpired() {
        return expirable && expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }
}
