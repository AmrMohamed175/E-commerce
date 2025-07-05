package org.os;

import java.util.*;

public class CheckoutService {
    public void checkout(Customer customer) {
        Cart cart = customer.getCart();

        if (cart.isEmpty()) {
            throw new RuntimeException("Cannot checkout: Cart is empty");
        }

        double subtotal = 0;
        double shippingFee = 0;
        List<Product> toShip = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.product;
            int qty = item.quantity;

            if (product.isExpired()) {
                throw new RuntimeException("Cannot checkout: " + product.getName() + " is expired");
            }

            if (qty > product.getQuantity()) {
                throw new RuntimeException("Cannot checkout: " + product.getName() + " is out of stock");
            }

            subtotal += product.getPrice() * qty;

            if (product.isShippable()) {
                for (int i = 0; i < qty; i++) {
                    toShip.add(product);
                    shippingFee += 10; // Flat shipping per item
                }
            }
        }

        double total = subtotal + shippingFee;

        if (customer.getBalance() < total) {
            throw new RuntimeException("Cannot checkout: Insufficient balance");
        }

        customer.deduct(total);

        for (CartItem item : cart.getItems()) {
            item.product.reduceQuantity(item.quantity);
        }

        if (!toShip.isEmpty()) {
            ShippingService.shipItems(toShip);
        }

        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            double itemTotal = item.quantity * item.product.getPrice();
            System.out.println(item.quantity + "x " + item.product.getName() + " " + itemTotal);
        }

        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shippingFee);
        System.out.println("Amount " + total);
        System.out.println("Remaining balance: " + customer.getBalance());
        System.out.println("END.\n");
    }
}
