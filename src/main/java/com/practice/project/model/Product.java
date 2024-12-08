package com.practice.project.model;

public final class Product {
    private final String name;
    private final double basePrice;
    private volatile Double finalPrice;
    public Product(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }
    private double calculateFinalPrice() {
        System.out.println("Calculating final price for " + name);
        return basePrice * 1.2;
    }
    public double getFinalPrice() {
        if (finalPrice == null) { // First check without locking
            synchronized (this) {
                if (finalPrice == null) { // Second check with locking
                    finalPrice = calculateFinalPrice();
                }
            }
        }
        return finalPrice;
    }
}