package org.os;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (quantity > product.getQuantity()) {
            throw new RuntimeException("Total quantity exceeds stock");
        }

        for (CartItem item : items) {
            // if already product is available in cart
            if (item.product.equals(product)) {
                if (item.quantity + quantity > product.getQuantity()) {
                    throw new RuntimeException("Total quantity exceeds stock");
                }
                item.quantity += quantity;
                return;
            }
        }
        // new product to be added in cart
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
