package org.os;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CheckoutService checkoutService = new CheckoutService();

        System.out.println("\n--- 1. Normal Checkout Case ---");
        try {
            Product cheese = new Product("Cheese", 100, 5, true, 0.4,  LocalDate.of(2025, 8, 1));
            Product biscuits = new Product("Biscuits", 150, 3, true, 0.7,  LocalDate.of(2025, 7, 10));
            Product tv = new Product("TV", 300, 2, true, 10.0,  null);
            Product scratchCard = new Product("Scratch Card", 50, 10, false, 0.0,  null);

            Customer amr = new Customer("Amr", 1000);
            Cart cart = amr.getCart();
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);

            checkoutService.checkout(amr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n--- 2. Cart Empty Case---");
        try {
            Customer empty = new Customer("Empty", 500);
            checkoutService.checkout(empty);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n---  3. Insufficient Balance Case ---");
        try {
            Product cheese = new Product("Cheese", 100, 5, true, 0.4,  LocalDate.of(2025, 8, 1));
            Customer broke = new Customer("Broke", 100);
            broke.getCart().add(cheese, 2); // subtotal 200 + 20 = 220
            checkoutService.checkout(broke);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n---  4. Expired Product Case ---");
        try {
            Product expiredCheese = new Product("Expired Cheese", 100, 2, true, 0.5,  LocalDate.of(2023, 1, 1));
            Customer expired = new Customer("Expired", 1000);
            expired.getCart().add(expiredCheese, 1);
            checkoutService.checkout(expired);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n--- 5. Out of Stock Case ---");
        try {
            Product tv = new Product("TV", 300, 1, true, 10.0,  null);
            Customer stock = new Customer("StockFail", 2000);
            stock.getCart().add(tv, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
