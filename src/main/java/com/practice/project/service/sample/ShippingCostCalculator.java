package com.practice.project.service.sample;

import com.practice.project.constant.ShippingType;

public class ShippingCostCalculator {
    public double calculateShippingCost(ShippingType shippingType, double weight) {
        return shippingType.getCost(weight);
    }
}
