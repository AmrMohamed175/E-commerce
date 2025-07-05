package org.os;

import java.util.*;

public class ShippingService {
    public static void shipItems(List<Product> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        Map<String, Integer> countMap = new HashMap<>();
        Map<String, Double> weightMap = new HashMap<>();

        for (Product item : items) {
            String name = item.getName();
            double weight = item.getWeight();
            totalWeight += weight;

            countMap.put(name, countMap.getOrDefault(name, 0) + 1);
            weightMap.put(name, weight);
        }

        for (String name : countMap.keySet()) {
            int count = countMap.get(name);
            double weight = weightMap.get(name) * count;
            System.out.println(count + "x " + name + " " + String.format("%.1f", weight) + "kg");
        }

        System.out.printf("Total package weight %.1fkg%n%n", totalWeight);
    }
}
